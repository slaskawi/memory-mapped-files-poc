FILES="*.dump"
for f in $FILES
do
	echo "Processing $f"
	hexdump -v -e '/1 "%02x "' -- $f | grep "54 68 69 73 20 69 73" && echo "$f contains this data :((((" &
done