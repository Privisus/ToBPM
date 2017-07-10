# Building
![](https://api.travis-ci.org/Privisus/ToBPM.svg?branch=master)

`gradlew clean build zip`  

# Contributing

If you feel like contributing to this piece of garbage but don't know what to contribute, here's some "ideas":

1. The process of parsing the BPM is still slow (about `1500 ms`for `1000 timings` and a whooping `32000 ms` for `5000 timings`).
2. Whenever the user parses their timings, it overwrites the current (previous) output. So maybe add an option to "overwrite" or not.
3. Add the "clear output" and "copy output" button.
4. Add an option to automatically copy the output once the user has pressed the parseButton.