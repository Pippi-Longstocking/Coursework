# AUTHOR:   Jacob Maniscalco
# DATE:     September 30, 2019
# PURPOSE:  This project creates a genetic algorithm for a traveling salesman problem.
#           I have used the networkx library to handle the digraph and included chr_helper.py
#           that includes functions to handle chromosome related actions.
#           networkx 1.10 link: https://networkx.github.io/documentation/networkx-1.10/install.html
import random
import sys
import networkx as nx
from chr_helper import *

# Save command line arguements
cities_file = sys.argv[1]
city_mileage_file = sys.argv[2]
population = int(sys.argv[3])
num_generations = int(sys.argv[4])

graph = nx.Graph()
nodes = []

# save city names to nodes
with open(cities_file, "r") as file:
    for line in file:
      nodes.append(line.rstrip('\n'))

STARTING_CITY = nodes[0]
# remove starting city from nodes
nodes.remove(nodes[0])
rows = population

# Creates weighted digraph with info from mileage file
with open(city_mileage_file) as mileage_file:
    for line in mileage_file:
        edge1, edge2, w = line.split()
        graph.add_edge(edge1, edge2, weight=int(w))

start_gen = generate_chromosomes(nodes, population)

 # appends path cost to end of chromosomes
append_fitness(graph, start_gen, STARTING_CITY, len(nodes))

generation = 0
while generation < num_generations:
    start_gen.sort(key=return_cost)
    # saves best 10 chromosomes from original
    next_gen = []
    # fills list with best chromosomes
    for i in range(10):
        next_gen.append(start_gen[i])
    # removes 10 best chromosomes from starting generation
    for i in range(10):
        start_gen.remove(next_gen[i])

    # genererates cross over chromosomes until starting generation runs out
    while len(start_gen) > 0 and len(next_gen) < population:
        # selects 10 random chromosomes from starting gen and sorts
        random_chromosomes = random.sample(start_gen, 10)
        random_chromosomes.sort(key = return_cost)

        best_random_chromosomes = []
        # saves best 4 chromosomes from random_chromosomes
        for i in range(0, 4):
            best_random_chromosomes.append(random_chromosomes[i])

        # chooses two random chromosomes from best random to crossover
        cross_chromosomes = random.sample(best_random_chromosomes, 2)
        new_chromsomes = crossover(cross_chromosomes, nodes)

        append_fitness(graph, new_chromsomes, STARTING_CITY, len(nodes))
        # appends new chromosomes to next generation
        for path in new_chromsomes:
            next_gen.append(path)

        # removes chromosomes from start_gen
        for path in random_chromosomes:
            start_gen.remove(path)

        # creates additional chromosomes
        add_chromosomes = generate_chromosomes(nodes, population - len(next_gen))
        append_fitness(graph, add_chromosomes, STARTING_CITY, len(nodes))
        for path in add_chromosomes:
            next_gen.append(path)
    start_gen = next_gen
    generation += 1

start_gen.sort(key=return_cost)

# prints best path and path weight
print "BEST PATH FOUND:"
for i in range(0, len(start_gen[0]) - 1):
    print path[i]
print "PATH DISTANCE: " + str(start_gen[0][len(nodes)])

