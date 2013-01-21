#!/usr/bin/perl

use strict;
use warnings;


my @list = (1 .. 4);
my @mult = map { $_ * 2 } @list;

print "@mult\n";
