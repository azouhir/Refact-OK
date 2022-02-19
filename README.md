# Refact-OK
Final Year Project - Computer Science - Brunel University - Software Metric collection tool

Refact-OK is a tool written in java that will allow you to gather some useful information on your project.
It is a refactoring tool that collects Software metrics such as: LOC, WMC, DIT, NOC, AVGCC, BUGS, CBO, Fan In, Fan Out, Blank Lines, Single and Multi Line Comments.

In order for the tool to work correctly you will need to enter the path to your:

SRC: source folder containing all the .java files in your project
BIN: bin folder containing all the .class files in your project

PLEASE ENSURE THE FOLDERS CONTAIN THE SAME FILES! This is crucial for your data collection.

For example is the file "Main.java" is present in src ensure that your bin folder contains "Main.class" and so on.
If your bin folder contains some extra computational classes such as "MainFrame$1" don't worry the tool won't consider those classes.

Below the instructions to work the tool:

1. Enter path to "bin" folder.
2. Enter path to "src" folder.
3. Click "Analyse" button.
4. Select one column and see the information on the middle left panel.
5. I you select another column make sure you select a different cell too in order for the info on the panel to be updated (This is a known bug).
6. Select one column and click "Ctrl" to select a second column, then hit "Correlation" button to get scatterplot and correlation.
7. If you want to manipulate the classes, enter a value in the metric you wish to manipulate and then tick the checkbox to lock the value then hit "Analyse".
8. Once you collected the classes you wanted click "Generate" button to get an excel file of your collected metrics.

This tool has been developed by ANAS ZOUHIR.
