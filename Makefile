build:
	javac -d ./bin ./src/App.java ./src/Runner.java ./src/MazeReader.java ./src/Maze.java ./src/Mouse.java ./src/Block.java ./src/Arah.java 

run: build
	java -cp .\bin App

