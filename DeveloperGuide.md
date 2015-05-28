**Table of Contents**


# 1.0 Prequisites #

You must have Ant 1.7.1 and Java 1.6 installed.

# 2.0 Installation #

## 2.1 Run Ant to install libraries ##

Check out the project using SVN or download the distribution and invoke ant at the top level of the project with the following command:

% ant

## 2.2 Verify installation ##

Verify the compilation of the project with the following command:

% ant -f verify.build.xml

This will run JUnit, Checkstyle, PMD, JavaDoc, and FindBugs.

# 3.0 Development Guidelines #

Once verification is completed successfully, you are ready to begin development.

  * Eclipse was used to develop this project with the formatting taken from [here](http://www2.hawaii.edu/~kchiogio/format.zip).

  * Before committing new changes, verify that the project still runs successfully using the verify command from section 2.2.