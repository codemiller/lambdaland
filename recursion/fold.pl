#!/usr/bin/perl

use strict;
use warnings;
use List::Util;

my $min_num = reduce { $a < $b ? $a : $b } 1..10;
my $min_str = reduce { $a lt $b ? $a : $b } 'aa'..'zz';
my $num_sum = reduce { $a + $b } 1 .. 10;
my $str_concat = reduce { $a . $b } 'a'..'z';
