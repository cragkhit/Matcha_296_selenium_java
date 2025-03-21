/*
 * {{{ header & license
 * Copyright (c) 2016 Farrukh Mirza
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 * }}}
 */

/**
 * @author Farrukh Mirza
 * 24/06/2016 
 * Dublin, Ireland
 */
package example.spi;

import java.io.OutputStream;
import java.util.List;

public interface Converter {

	public void convertHtmlToPdf(List<String> htmls, OutputStream out);

	public void convertHtmlToPdf(List<String> htmls, String css, OutputStream out);

	
	public void convertHtmlToPdf(String html, OutputStream out);

	public void convertHtmlToPdf(String html, String css, OutputStream out);
}
