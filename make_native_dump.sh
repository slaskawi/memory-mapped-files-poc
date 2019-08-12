PID=$(jps | grep MainClass | cut -d" "  -f1)
grep rw-p /proc/$PID/maps \
| sed -n 's/^\([0-9a-f]*\)-\([0-9a-f]*\) .*$/\1 \2/p' \
| while read start stop; do \
    gdb --batch --pid $PID -ex \
        "dump memory $PID-$start-$stop.dump 0x$start 0x$stop"; \
done