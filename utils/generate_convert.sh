#!/bin/bash

# Convert high-res mp4 to low res mkv
# ffmpeg options taken from:
# https://opensource.com/article/17/6/ffmpeg-convert-media-file-formats
# https://ffmpeg.org/ffmpeg-utils.html#Video-size
# e.g. -s svga -s hd480
SCRIPT='/tmp/convert.sh'
# e.g.
# SIZE='svga'
# SIZE='hd480'
SIZE=${1:-hd480}
find . -iname '*mp4' |sort | while read filename ; do D=$(dirname "$filename"); S=$(basename "$filename"); T="${S/mp4/mkv}" ;  echo "pushd '${D}'"; echo "echo \"Converting \\\"${S}\\\"\""; echo "if [[ ! -f \"$T\" ]] ; then ffmpeg -i \"${S}\" -c:v vp9 -s ${SIZE} -v 0 \"${T}\"; fi" ; echo popd ;  done | tee $SCRIPT
chmod +x $SCRIPT
$SCRIPT
