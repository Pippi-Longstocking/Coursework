import random

# PURPOSE:          Finds the path length for all members of list and appends the cost to the end of the list
# PARAMETER(S):
#   graph:            Graph object from networkx
#   list:             list of chromosomes
#   starting_city:    First city found in cities txt file
#   num_cities:       number of cities in graph
# RETURNS:          none
def append_fitness(graph, list, starting_city, num_cities):
    index = 0
    for path in list:
        cost = 0
        i = 0
        while i in range(num_cities - 2):
            edge = graph.get_edge_data(path[i], path[i+1])
            edge_weight = edge['weight']
            cost += int(edge_weight)
            i += 1

        # adds distance from start/end cities to first city in list
        start_edge = graph.get_edge_data(starting_city, list[index][0])
        end_edge = graph.get_edge_data(starting_city, list[index][num_cities - 2])
        start_weight = start_edge['weight']
        end_weight = end_edge['weight']
        cost += int(start_weight)
        cost += int(end_weight)

        # replace cost to end of list
        list[index][num_cities] = cost
        index += 1

# PURPOSE:  Returns the path cost for the chromosome. Used as the key for sort function
def return_cost(value):
    return value[(len(value) - 1)]

# PURPOSE:      Creates a 2d list with randomly generated chromosomes (has a path cost of 0 appended at the end)
# PARAMETER(S):
#   nodes:      list of cities in graph
#   population: size of population
def generate_chromosomes(nodes, population):
    # creates 2d list
    chromosomes = [["" for j in range(len(nodes))] for i in range(population)]
    # populates list with random chromosomes
    for x in range(population):
        random_nodes = random.sample(nodes, len(nodes))
        chromosomes[x] = random_nodes
    # appends a path cost of 0 to end of the path
    for path in chromosomes:
        path.append(int('0'))

    return chromosomes

# PURPOSE:          Performs a crossover of the two chromosomes passed in chromosomes.
# PARAMETER(S):
#   chromosomes:    Contains two chromosomes that must be crosses.
#   cities:         Contains the list of cities.
def crossover(chromosomes, cities):
    cities_1 = []
    cities_2 = []
    dup_1 = []
    dup_2 = []
    chr1 = chromosomes[0]
    chr2 = chromosomes[1]
    chr1_p1 = []
    chr1_p2 = []
    chr2_p1 = []
    chr2_p2 = []

    # copies cities to new lists
    for city in cities:
        cities_1.append(city)
        cities_2.append(city)

    # generates crossover point
    point = random.randrange(1, len(chr1) - 2)
    # removes path length to simplify crossover
    chr1.pop(len(chr1) - 1)
    chr2.pop(len(chr2) - 1)

    # saves sublist of cities
    for i in range(0, point + 1):
        chr1_p1.append(chr1[i])
        chr2_p1.append(chr2[i])
    for i in range(point + 1, len(chr1)):
        chr1_p2.append(chr1[i])
        chr2_p2.append(chr2[i])

    # creates new chromosomes from cross over
    new_chr1 = chr1_p1 + chr2_p2
    new_chr2 = chr2_p1 + chr1_p2

    # obtains duplicated and unrepresented cities and stores those in cities_1 and dup_1
    for i in range(len(cities_1)):
        # if city is not duplicated remove city from all cities
        if new_chr1[i] in cities_1:
            cities_1.remove(new_chr1[i])
        # if city is duplicated, add to duplicate list
        else:
            dup_1.append(new_chr1[i])

    # repopulates duplicated cities with unrepresented cities
    while len(cities_1) > 0:
        unrep_city = random.choice(cities_1)
        dup_city = random.choice(dup_1)
        # searches chromosome for duplicate city and replaces it
        for i in range(len(new_chr1)):
            if new_chr1[i] == dup_city:
                new_chr1[i] = unrep_city
                dup_1.remove(dup_city)
                break

        # removes appropriate cities
        cities_1.remove(unrep_city)
    # obtains duplicated and unrepresented cities and stores those in cities_2 and dup_2
    for i in range(len(cities_2)):
        # if city is not duplicated remove city from all cities
        if new_chr2[i] in cities_2:
            cities_2.remove(new_chr2[i])
        # if city is duplicated add to duplicate list
        else:
            dup_2.append(new_chr2[i])
    # repopulates duplicated cities with unrepresented cities
    while len(cities_2) > 0:
        unrep_city = random.choice(cities_2)
        dup_city = random.choice(dup_2)
        # searches chromosome for duplicate city and replaces it
        for i in range(len(new_chr2)):
            if new_chr2[i] == dup_city:
                new_chr2[i] = unrep_city
                dup_2.remove(dup_city)
                break
        cities_2.remove(unrep_city)

    # creates new list that contains final crossover chromosomes
    new_chromosomes = []
    new_chromosomes.append(new_chr1)
    new_chromosomes.append(new_chr2)
    for path in new_chromosomes:
        path.append('0')

    return new_chromosomes
