#!/bin/bash
        COUNT=0
        for i in {1..5}; do
            N=`grep -o $i confusion | wc -l`
            echo $i $N
            COUNT=$(($COUNT+$N))
        done
        echo $COUNT
