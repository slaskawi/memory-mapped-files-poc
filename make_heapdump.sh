jcmd $(jps | grep MainClass | cut -d" "  -f1) GC.heap_dump "$(date +'%m_%d_%Y_%H_%M_%S')".hprof
