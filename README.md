# Defense of Homework Assignments

A simulation of homework defense for students using threads for the assistant and professor.

## Description

This project simulates the process of defending homework assignments, where students arrive randomly within a 5-second window to defend their work. The professor can supervise two defenses simultaneously, while the assistant can handle only one student at a time. The goal is to evaluate and grade students within the given time frame.

## Rules

- The professor supervises **two students** in parallel, while the assistant evaluates **one student** at a time.
- Defenses can only occur within **5 seconds** of starting the process.
- Students arrive at random times between **0 to 1 second** after the defense begins.
- Each student's defense lasts between **0.5 and 1 second**.
- Defenses are interrupted if the **5-second limit** is exceeded.
- Each student receives a grade between **5 and 10**.

## Features

- **Multithreading**: Thread pools are used to simulate assistant and professor threads.
- **CyclicBarrier**: The professor waits for two students to be ready before starting their defenses simultaneously.
- **Randomized Arrival**: Students arrive randomly within the allowed window.
- **Randomized Defense Time**: Each defense takes a random time to complete.
- **Thread-Safe Grading**: Final grades are calculated safely using synchronization mechanisms.


