package auth;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthSchemeProvider;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.impl.auth.SPNegoSchemeFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Scanner;
import java.util.Set;

public class HttpClientKerberosDoAS {

    public static void main(String[] args) throws Exception {
        System.setProperty("java.security.auth.login.config", HttpClientKerberosDoAS.class.getResource("/jaas_login.conf").toExternalForm());
        System.setProperty("javax.security.auth.useSubjectCredsOnly", "false");
        System.setProperty("sun.security.krb5.debug", "true");

        String url = "";

        if (args.length == 1) {
            url = args[0];
            HttpClientKerberosDoAS kcd = new HttpClientKerberosDoAS();

            kcd.test(url);
        }
        else {
            System.out.println("run with User Password URL");
        }

    }

    public void test(final String url) {
        try {

            LoginContext loginCOntext = new LoginContext("com.sun.security.jgss.login", new KerberosCallBackHandler());
            loginCOntext.login();
            Subject.doAs(loginCOntext.getSubject(), (PrivilegedAction<Object>) () -> {
                try {

                    Subject current = Subject.getSubject(AccessController.getContext());
                    System.out.println("----------------------------------------");
                    Set<Principal> principals = current.getPrincipals();
                    for (Principal next : principals) {
                        System.out.println("DOAS Principal: " + next.getName());
                    }
                    System.out.println("----------------------------------------");

                    call(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            });

        } catch (LoginException le) {
            le.printStackTrace();
        }
    }

    private void call(String url) throws IOException {
        HttpClient httpclient = getHttpClient();

        try {

            HttpUriRequest request = new HttpGet(url);
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println("----------------------------------------");

            System.out.println("STATUS >> " + response.getStatusLine());

            if (entity != null) {
                System.out.println("RESULT >> " + EntityUtils.toString(entity));
            }

            System.out.println("----------------------------------------");

            EntityUtils.consume(entity);

        } finally {
            httpclient.getConnectionManager().shutdown();
        }
    }

    private HttpClient getHttpClient() {

        Credentials use_jaas_creds = new Credentials() {
            public String getPassword() {
                return null;
            }

            public Principal getUserPrincipal() {
                return null;
            }
        };

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(null, -1, null), use_jaas_creds);
        Registry<AuthSchemeProvider> authSchemeRegistry = RegistryBuilder.<AuthSchemeProvider>create().register(AuthSchemes.SPNEGO, new SPNegoSchemeFactory(true)).build();
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultAuthSchemeRegistry(authSchemeRegistry).setDefaultCredentialsProvider(credsProvider).build();

        return httpclient;
    }

    class KerberosCallBackHandler implements CallbackHandler {

        //        private final String user;
//        private final String password;
        private final Scanner scanner;

        public KerberosCallBackHandler() {
            scanner = new Scanner(System.in);
//
//                    this.user = user;
//            this.password = password;
        }

        public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

            for (Callback callback : callbacks) {

                if (callback instanceof NameCallback) {
                    NameCallback nc = (NameCallback) callback;
                    System.out.print("User name:");
                    String username = scanner.next();
                    nc.setName(username);
                }
                else if (callback instanceof PasswordCallback) {
                    PasswordCallback pc = (PasswordCallback) callback;
                    System.out.print("Password:");
                    String password = scanner.next();
                    pc.setPassword(password.toCharArray());
                }
                else {
                    throw new UnsupportedCallbackException(callback, "Unknown Callback");
                }

            }
        }
    }

}