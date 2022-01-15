# Evolution Generator project
## Description
The goal of this project is to make animals evolution animation.

### Animals
Every single animal has its own genotype, energy, eat plants, can reproduce with other animals.

### Map
Map represent grassland with jungle in the center.

### Day
One simulated day contains a few steps:
1) Remove death animals in map,
2) Move every animal,
3) Eating (At one position the plant is eaten by the strongest animal and gains energy)
4) Animals reproduction by two animals with the highest energy level in one position. If there are more than two with highest level, the animals will be choosen randomly.
5) Adding new plants to map in grassland and jungle.

## Animation Features
* The simulation can be paused, resumed.
* The animal can be tracked for specific amount of days to see how many children and grandchildren it will have.
* You can see map statistics.
* The statistics can be also saved to .txt file.
* Animals with the most common genotype can be highlighted.
* In one window two maps can be simulated separately.

## Technologies
* Java
* JavaFX