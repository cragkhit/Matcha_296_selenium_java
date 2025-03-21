#!/bin/bash

# Convert high-res mp4 to low res mkv
# ffmpeg options taken from:
# https://opensource.com/article/17/6/ffmpeg-convert-media-file-formats
# https://ffmpeg.org/ffmpeg-utils.html#Video-size
# e.g. svga, qhd, hd480, hd720
# default is qhd which is scaled down hd720 with same aspect raio. 
# videos converted from hs720 to svga is best viewed with aspect ratio of 16:9
# lynx -dump https://ffmpeg.org/ffmpeg-utils.html#Video-size 2>&1 |sed -n '/Video size/,/Video rate/p'
# see also: https://www.maketecheasier.com/manipulate-html-and-xml-files-from-commnad-line/
# to calculate the new video size based on the current use
# use https://www.commandlinefu.com/commands/view/2207/get-video-information-with-ffmpeg
# e.g. ffmpeg -i or ffprobe -show_streams 

# following is the complete list of abbreviations recognized by ffmpeg:

# 'sqcif' 128x96
# 'qqvga' 160x120
# 'qcif' 176x144
# 'hqvga' 240x160
# 'cga' 320x200
# 'qvga' 320x240
# 'cif' 352x288
# 'film' 352x240
# 'ntsc-film' 352x240
# 'qntsc' 352x240
# 'qpal' 352x288
# 'wqvga' 400x240
# 'fwqvga' 432x240
# 'hvga' 480x320
# 'ega' 640x350
# 'nhd' 640x360
# 'sntsc' 640x480
# 'vga' 640x480
# '4cif' 704x576
# 'ntsc' 720x480
# 'pal' 720x576
# 'spal' 768x576
# 'svga' 800x600
# 'hd480' 852x480
# 'wvga' 852x480
# 'qhd' 960x540
# 'xga' 1024x768
# 'hd720' 1280x720
# 'sxga' 1280x1024
# 'wxga' 1366x768
# '16cif' 1408x1152
# 'uxga' 1600x1200
# 'wsxga' 1600x1024
# 'hd1080' 1920x1080
# 'wuxga' 1920x1200
# '2kflat' 1998x1080
# '2k' 2048x1080
# '2kdci' 2048x1080
# '2kscope' 2048x858
# 'qxga' 2048x1536
# 'qsxga' 2560x2048
# 'woxga' 2560x1600
# 'wqsxga' 3200x2048
# 'uhd2160' 3840x2160
# 'wquxga' 3840x2400
# '4kflat' 3996x2160
# '4k' 4096x2160
# '4kdci' 4096x2160
# '4kscope' 4096x1716
# 'hsxga' 5120x4096
# 'whsxga' 6400x4096
# 'uhd4320' 7680x4320
# 'whuxga' 7680x4800
# 
#
# based on: https://gist.github.com/cosimo/3760587

# custom sizes:
# '1280x800' => '852x532'
OPTS=`getopt -o dvnhibks:e:a: --long debug,verbose,dry-run,help,inspect,battery,keep,size:,extension:,name: -n 'parse-options' -- "$@"`
if [ $? != 0 ] ; then echo "Failed parsing options." >&2 ; exit 1 ; fi
# TODO: dirscan
# EXTENSION='*mp4'
# NAME='*filename*'
# find . -iname "$NAME.$EXTENSION" | sed 's|\([^.]\)/.*$|\1|g'|sort -u
VERBOSE=false
HELP=false
DRY_RUN=false
KEEP_SIZE=false
BATTERY_CHECK=false
SIZE='qhd'
EXTENSION='mp4'
NAME='*'

while true; do
  case "$1" in
    -d | --debug )     DEBUG=true; shift ;;
    -v | --verbose )   VERBOSE=true; shift ;;
    -i | --inspect )   INSPECT=true; shift ;;
    -a | --name )      NAME="*$2*"; shift ;;
    -h | --help )      HELP=true; shift ;;
    -n | --dry-run )   DRY_RUN=true; shift ;;
    -b | --battery )   BATTERY_CHECK=true; shift ;;
    -s | --size )      SIZE="$2"; shift; shift ;;
    -k | --keep )      KEEP_SIZE=true; shift ;;
    -e | --extension ) EXTENSION="$2"; shift; shift ;;
    -- ) shift; break ;;
    * ) break ;;
  esac
done
if [[ "${HELP}" = "true" ]]; then
cat <<EOF
Usage:
  $0 [-d|--debug] [-h|--help] [-i|--inspect] [-n|--dry-run] [-b|--battery] [-k|--keep] [-v|--verbose] [-s|--size] SIZE [-a|--name] NAME [-e|--extension] EXTENSION
EOF
exit 0
fi
if [[ "${VERBOSE}" = "true" ]]; then
  echo VERBOSE=$VERBOSE
  echo INSPECT=$INSPECT
  echo HELP=$HELP
  echo DRY_RUN=$DRY_RUN
  if $KEEP_SIZE ; then
    echo 'keep the original size'
  else
    echo SIZE=$SIZE
  fi
  echo EXTENSION=$EXTENSION
  echo BATTERY_CHECK=$BATTERY_CHECK
fi

SCRIPT="/tmp/convert.$$.sh"
cat <<EOF>>$SCRIPT
check_remaining_battery () {
STATE=\$(upower -i \$(upower -e | grep -i battery | head -1)| grep -E 'state: *' | awk '{print \$2}')
MINUTES=\$(upower -i \$(upower -e | grep -i battery | head -1)| grep 'time to empty' | grep minutes | awk '{print \$4}' | sed 's|\.[0-9][0-9]*||')
if [[ "\${STATE}" -eq 'discharging' ]]
# when the battery is charging the time to empty is meaningless
# NOTE: == / != is better than -eq / -ne , since -ne does not work
then
  if [[ ! -z \$MINUTES ]]
  then
    if [ \$MINUTES -lt 5 ]
    then
      echo 'Too little battery left - aborting'
      exit 0
    fi
  fi
fi
}
EOF
# NOTE: to set DEBUG need to export it from the calling shell
if [[ -z "${DEBUG}" ]] ; then
  echo 'DEBUG was not set explicitly, default is false'
  DEBUG='false'
fi
echo "DEBUG=${DEBUG}"
if [[ "${DEBUG}" = 'true' ]] ; then
  echo "Finding files \"*${EXTENSION}\""
fi
if [[ "${INSPECT}" = 'true' ]]; then
  if [[ "${DEBUG}" = 'true' ]]; then
    echo "Inspecting first file \"*${EXTENSION}\""
  fi
  # ffprobe -select_streams v -show_streams "$S" 2>&1 | sed -n 's|coded_height=\(.*\)$|\1|p'
  S=$(find . -iname "${NAME}.${EXTENSION}" | head -1)
  H=$(ffprobe -select_streams v -show_streams "$S" 2>&1 | sed -n 's|coded_height=\(.*\)$|\1|p')
  W=$(ffprobe -select_streams v -show_streams "$S" 2>&1 | sed -n 's|coded_width=\(.*\)$|\1|p')

  CODED="${W}x${H}"
  if [[ "${DEBUG}" = 'true' ]]; then
    echo "height=$H"
    echo "width=$W"
    echo "coded dimensions=$CODED"
  fi

  SIZES=( '1024x768:svga'
    # 667x500 
    '1024x576:hd480'
    '1920x1080:qhd'
    '800x600:spal'
    '1278x718:qhd'
    '1280x720:qhd'
    '1280x800:852x532')
  
  SIZE='?'
   
  for ENTRY in "${SIZES[@]}" ; do
    KEY="${ENTRY%%:*}"
    VALUE="${ENTRY##*:}"
    if [[ "${CODED}" = "${KEY}" ]] ; then
      SIZE=$VALUE
    fi
  done
  echo $CODED
  echo $SIZE
  exit 0
fi
# technically able to descend, seldom used
find . -iname "${NAME}.${EXTENSION}" | sort | while read filename ; do
  D=$(dirname "$filename")
  S=$(basename "$filename")
  N="${S/.${EXTENSION}/.temp.mkv}"
  T="${S/.${EXTENSION}/.mkv}"
  if [[ "$D" != '.' ]]
  then
    echo ">/dev/null pushd '$D'"
  fi
  echo "if [[ ! -f \"$T\" ]];"
  echo '  then'
  if [[ "${BATTERY_CHECK}" = 'true' ]] ; then
    echo 'check_remaining_battery'
  fi
  echo "echo \"Converting \\\"$S\\\"\""
  if $KEEP_SIZE ; then 
    echo " ffmpeg -i \"$S\" -c:v vp9 -v 0 \"$T\""
  else
    if [[ $EXTENSION == 'mov' ]] ; then
      echo " ffmpeg -i \"$S\" -map 0 -c copy \"$N\" &> /dev/null"
      echo " ffmpeg -i \"$N\" -c:v vp9 -s ${SIZE} -v 0 \"$T\""
      echo " rm -f \"$N\""
    else
      echo " ffmpeg -i \"$S\" -c:v vp9 -s ${SIZE} -v 0 \"$T\""
    fi
  fi
  # TODO:
  # echo " ffprobe -show_format -v quiet \"$T\" | grep -i duration| sed 's/duration=//'"
  echo 'fi'
  if [[ "$D" != '.' ]]
  then
    echo '>/dev/null popd'
  fi
done | tee -a $SCRIPT
chmod +x $SCRIPT
if [[ "${DEBUG}" = 'true' ]]
then
  echo "Generated ${SCRIPT}"
else
  if [[ "${DRY_RUN}" = "true" ]]; then
    echo "Generated ${SCRIPT}"
  else
    echo "Running ${SCRIPT}"
    $SCRIPT
    rm -f $SCRIPT
  fi
fi
# TODO:
# find . -iname '*mp3' | xargs -IX ffprobe -show_format -v quiet X | grep 'track=' |sort
# NOTE: may be empty
# NOTE: xargs: unmatched single quote; by default quotes are special to xargs unless you use the
# find . -iname '*mp3' -exec ffprobe -show_format -v quiet {} 2>&1 \; | grep 'track *'
# find . -iname '*mp3' -exec ffprobe -show_format {} 2>&1  \; | grep -E '(track|title) *:'

