set rmargin 13
set key outside
set yrange [-2.49656778127151:8.46108965456123]
set ylabel "Predicted {/Symbol D}G (kcal/mol)"
set xlabel "Sequence position (of center of helix)"
set ter png enh interlace size 2760,1932 font 'Nimbus,40'
set out "/tmp/vtItbj7CpO/plots/plot.png"
set style line 1 lc rgb "#DDDDDD"
plot [0:110] "/tmp/vtItbj7CpO/plots/helices.list" w boxes ls 1 fill solid noborder notitle, 0 notitle w l 0, "/tmp/vtItbj7CpO/plots/dG_curve_19.plot" w l lw 6 t "L=19", "/tmp/vtItbj7CpO/plots/dG_curve_20.plot" w l lw 6 t "L=20", "/tmp/vtItbj7CpO/plots/dG_curve_21.plot" w l lw 6 t "L=21", "/tmp/vtItbj7CpO/plots/dG_curve_22.plot" w l lw 6 t "L=22", "/tmp/vtItbj7CpO/plots/dG_curve_23.plot" w l lw 6 t "L=23" 
