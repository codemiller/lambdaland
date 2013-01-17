#!/usr/bin/perl

use strict;
use warnings;

my @numbers = (8, 2, 5, 3, 1, 7);
my @big_numbers = grep { $_ > 4 } @numbers;

print "@big_numbers\n";
