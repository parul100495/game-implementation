# ThreeInARow
This is a basic Java implementation of the Three in a Row game.

### How to build and test (from Terminal):

1. Make sure that you have Apache Ant installed.

2. Run each ant command in the threeinarow folder, which contains the `build.xml` build file.

2. Run `ant document` to generate the javadoc (a hypertext description) for all of the java classes. Generated hypertext description will be in the `jdoc` folder. Open the `index.html` file. 

3. Run `ant compile` to compile all of the java classes. Compiled classes will be in the `bin` folder.

4. Run `ant test` to run all unit tests.

### How to run (from Terminal):

1. After building the project (i.e., running `ant`), run the following command in the threeinarow folder:
   `java -cp bin RowGameApp`. Default configuration is ThreeinaRow with 3*3 grid.
   
2. Grid size can be changed to dynamic using CLI arguments as `java -cp bin RowGameApp ThreeInARow 4 4`.

### How to clean up (from Terminal):

1. Run `ant clean` to clean the project (i.e., delete all generated files).

# TicTacToe
This is a basic Java implementation of the Tic-Tac-Toe game.

### How to build and test (from Terminal):

1. Make sure that you have Apache Ant installed.

2. Run each ant command in the threeinarow folder, which contains the `build.xml` build file.

2. Run `ant document` to generate the javadoc (a hypertext description) for all of the java classes. Generated hypertext description will be in the `jdoc` folder. Open the `index.html` file. 

3. Run `ant compile` to compile all of the java classes. Compiled classes will be in the `bin` folder.

4. Run `ant test` to run all unit tests.

### How to run (from Terminal):

1. After building the project (i.e., running `ant`), run the following command in the threeinarow folder:
   `java -cp bin RowGameApp TicTacToe 4 4`.

### How to clean up (from Terminal):

1. Run `ant clean` to clean the project (i.e., delete all generated files).
