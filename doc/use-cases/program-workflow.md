```plantuml
@startuml
title Full Program Workflow

skin rose
|#technology|Chair|
|#implementation|System|
|#NavajoWhite|Coach Applicants|
|#MediumTurquoise|CS Faculty|

|Chair|
start
:Input classes, schedules, and coaches needed;
|System|
:Display classes and schedules;
|Coach Applicants|
:Select and rank class choices;
|System|
:Store results from coach applicants;
:Display coach applicants;
|CS Faculty|
:Select and rank coach choices;
|System|
:Store results for coach choices;
:Run algorithm for coach selection;
|Chair|
:Make final edits and selections;
:Submit final selections;
stop
@enduml
```