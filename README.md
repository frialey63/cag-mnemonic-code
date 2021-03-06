# Java implementation of the City &amp; Guilds Mnemonic Code

## Background

This project is a Java implementation of the City & Guilds Mnemonic Code language which originates from 1964.
The language was the first that I ever encountered as part of my Computer Science A-Level in 1981 at Ashmole School.
My year was the first at the school to include a full class in this new A-Level.
As I recall, most of our usage of this language was in the form of "paper work" exercises.
We did have a facility by which programs could be dispatched to the computer centre at Barnet College for entry on the mainframe and then accessed remotely using the teleterminal at the school.
(This was certainly the way in which I developed code for my later FORTRAN project.)
I have never forgotten the Mnemonic Code language and a summary paper "Appendix B" has resided in my pile of papers as a TODO item for many years.
Note I thought that I had some examples of my work in the language stored in a box in my loft but sadly this does not appear to be the case although the search will continue...
However, with some extra time on my hands now it seemed a good time to resurrect the language in Java, the modern language which has been the foundation of my career in recent years.

For those who are not familiar (and there will be very few who are!) the Mnemonic Code is an assembly language of the most primitive form.
It is an language which appears to have been invented for instruction only, i.e. it was never directly linked with the machine language of any computer hardware.
As previously noted, it originated in 1964 at Elliott Computers where it was implemented on the Elliott 903 computer.  The first reference below describes this implementation and is the primary source for my development of this Java implementation.
The data processing business of Elliott Computers was taken over by ICT (aka ICL) in 1968 and so knowledge of the language transferred to ICL.
At ICL in 1967 it was specified into a form which was suitable for incorporation into the City & Guilds examinations 319 and 320.
This specification is closely related to the original Elliott implementation of 1964.
A bit later in 1968 the specification was revised by ICL also for the City & Guilds examination 319, see references below.

So by the time I encountered the Mnemonic Code in 1980 it was already a fairly old language.
At some time in the lead-up to 1980 the AEB examination board in England decided to adopt the City & Guilds Mnemonic Code language to support the teaching of their syllabus.

*Dedicated to Mr Cook and Mr Cooke my computer science and mathematics teachers at Ashmole School.*

## Design Notes

The scope of this Java implementation is strictly that of the machine independent aspects of the Mnemonic Code language.
No attempt has been made to simulate the behaviour of the Elliott 903 host involving its numeric representation, math library and character set.
(The details of these are available in the references and more knowledgeable programmers have undertaken this development for other projects.)
Similarly the operational procedures involved in controlling the translator and interpreter on the Elliott 903 are outside the scope of this Java implementation.

The Mnemonic Code is a simple language and consequently the Java software necessary for its implementation is relatively simple.
For the technically inclined there are a couple of interesting features of the software design which are worth noting.
The assembly process performed by the Assembler class makes use of pattern matching with regular expressions to parse the input text and extract Orders, i.e. instructions and directives.
The interpretation process performed by the Interpreter class utilises reflection to lookup the machine instruction corresponding to a stored instruction.
Instructions are mechanised through a common Executable interface which defines an "execute" operation.
A significant focus of the development effort was to accurately reproduce the I/O instructions in group 5 albeit with Java devices and files.
Also, the error checking as described for the original Elliot implementation has been faithfully reproduced just with the addition of a full description in the error report.

A Java 8 JDK was used to implement the software and it was developed using the Eclipse 2020 IDE in conjunction with the Maven 3.6.3 build system.
JUnit test coverage is 90%, a custom Checkstyle ruleset (derived from Sun) has been imposed and static code analysis has been applied with FindBugs (default settings).
The repo includes the Eclipse project files and when imported as a Maven project it will build automatically and launchers are provided to run the JUnit tests and one example program.
Of course it is also possible to build the software without Eclipse by means of the usual Maven incantations.

## Usage

    Option       Description
    ------       -----------
    -a           assemble only
    -d           dump contents of store
    -f <String>  file for data
    -t           trace enable

## References

### Andrew Herbert's Documentation Archive

[The City and Guilds Computer](https://andrewjherbert.github.io/Elliott-900-documentation/The%20City%20and%20Guilds%20Computer.pdf)

[City & Guilds 319 Mnemonic Code (1967)](https://andrewjherbert.github.io/Elliott-900-documentation/City%20and%20Guilds%20319%20Mnemonic%20Code%20%281967%29.pdf)

[City & Guilds 319 Mnemonic Code (1968)](https://andrewjherbert.github.io/Elliott-900-documentation/City%20and%20Guilds%20319%20Mnemonic%20Code%20%281968%29.pdf)

[Elliott 900 Series Computers](https://andrewjherbert.github.io/Elliott-900-documentation/)

### Elliott Computers

[Terry Froggatt's Home Page](http://www.tjfroggatt.plus.com/)

[Elliott Brothers](https://dogedaos.com/wiki/Elliott_Automation.html)

[Elliott 803 Simulation](http://elliott803.sourceforge.net/)

### Computer Conservation Society

[Resurrection - The CCS Journal](https://www.computerconservationsociety.org/resurrection.htm)

[Computer Resurrection Issue 71](https://www.computerconservationsociety.org/resurrection/res71.htm)

[City & Guilds Mnemonic Code](https://www.computerconservationsociety.org/resurrection/res71.htm#f)

### Centre for Computing History

[Centre for Computing History](http://www.computinghistory.org.uk)

[Memories of a Programmer - Helen Young](http://www.computinghistory.org.uk/articles/32.htm)

[Computing in Schools - The Early Years (by Tony Goodhew)](http://www.computinghistory.org.uk/userdata/files/computing_in_schools_-_the_early_years.pdf)

### ICL Computer Education in Schools

[ICL Computer Education in Schools](https://iclces.uk/index.html#)

[City & Guilds Mnemonic Code](https://iclces.uk/articles/city_and_guilds_mnemonic_code.html)

[Originals](https://iclces.uk/articles/city_and_guilds_mnemonic_code_originals.html)
