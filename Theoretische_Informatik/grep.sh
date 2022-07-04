#!/bin/bash  

echo "Hello, this is a script using grep!"
echo "Output:"

f1="regex-class/macbeth-acti.txt"
f2="regex-class/basic/words.txt"
f3="regex-class/text.txt"
f4="regex-class/classes/phonenumbers.txt"

# Exercise 3

# grep -P "\b[A-Z]{2}\w*" $f1 --color=always; 
# grep -P "\w*[uz]\b" $f1 --color=always; 
# grep -P "\b[aeiou]{2}\w*[aeiou]{2}\b" $f1 --color=always; 
# grep -P "\d{2,}\D+\d{2,}" $f1 --color=always; 
# grep -P ".*\bthe\b.*\bthe\." $f1 --color=always; 

# Exercise 4

# grep -P "\b([A-Z]).*?\1" $f1 --color=always;
# grep -P "(\b\w+\b).*\b\1$" $f1 --color=always;

# probably wrong
# grep -P "\b,.*+," $f1 --color=always;