#include <mpi.h>
#include <iostream>
#include <vector>
#include <algorithm>

// Function to compute the Collatz sequence length
long collatz_sequence_length(long n) {
    long length = 1;
    while (n != 1) {
        if (n % 2 == 0) {
            n /= 2;
        } else {
            n = 3 * n + 1;
        }
        length++;
    }
    return length;
}

// Function to find the number with the longest Collatz sequence in a given range
std::pair<long, long> find_longest_collatz_sequence(long start, long end) {
    long max_length = 0;
    long max_number = start;
    for (long i = start; i <= end; ++i) {
        long length = collatz_sequence_length(i);
        if (length > max_length) {
            max_length = length;
            max_number = i;
        }
    }
    return std::make_pair(max_number, max_length);
}

int main(int argc, char *argv[]) {
    MPI_Init(&argc, &argv);

    int rank, size;
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    if (size < 2) {
        if (rank == 0) {
            std::cerr << "This program requires at least two processes." << std::endl;
        }
        MPI_Finalize();
        return 1;
    }

    // Define the range [a, b]
    long a = 1;
    long b = 10000000;

    // Measure start time
    double start_time = MPI_Wtime();

    // Split the range among the processes
    long local_start = a + rank * (b - a + 1) / size;
    long local_end = a + (rank + 1) * (b - a + 1) / size - 1;

    if (rank == size - 1) {
        local_end = b;
    }

    // Each process finds the longest Collatz sequence in its range
    auto local_result = find_longest_collatz_sequence(local_start, local_end);
    long local_max_number = local_result.first;
    long local_max_length = local_result.second;

    if (rank == 0) {
        // Root process
        std::vector<long> global_max_numbers(size);
        std::vector<long> global_max_lengths(size);

        // Collect results from all processes, including itself
        global_max_numbers[0] = local_max_number;
        global_max_lengths[0] = local_max_length;

        for (int i = 1; i < size; ++i) {
            MPI_Recv(&global_max_numbers[i], 1, MPI_LONG, i, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
            MPI_Recv(&global_max_lengths[i], 1, MPI_LONG, i, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
        }

        // Determine the global maximum length and corresponding number
        auto max_element_iter = std::max_element(global_max_lengths.begin(), global_max_lengths.end());
        long overall_max_length = *max_element_iter;
        int index = std::distance(global_max_lengths.begin(), max_element_iter);
        long overall_max_number = global_max_numbers[index];

        std::cout << "The number " << overall_max_number << " has the longest Collatz sequence length of " 
                  << overall_max_length << " in the range [" << a << ", " << b << "]." << std::endl;

        // Measure end time and calculate elapsed time
        double end_time = MPI_Wtime();
        double elapsed_time = end_time - start_time;

        std::cout << "Time taken with " << size << " processes: " << elapsed_time << " seconds." << std::endl;

    } else {
        // Non-root processes send their results to the root process
        MPI_Send(&local_max_number, 1, MPI_LONG, 0, 0, MPI_COMM_WORLD);
        MPI_Send(&local_max_length, 1, MPI_LONG, 0, 1, MPI_COMM_WORLD);
    }

    MPI_Finalize();
    return 0;
}
