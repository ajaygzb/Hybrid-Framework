@ECHO OFF
CD %cd%
echo %CD%
echo.
echo.
echo.
echo.
:MENU


ECHO                                           ============= BMS Automation =============
echo.
echo.

ECHO                                           -------------------------------------
ECHO                                                         1.  Smoke
ECHO                                           -------------------------------------
ECHO                                                         2.  Sanity
ECHO                                           -------------------------------------
ECHO                                                         3.  CompleteRun
ECHO                                           -------------------------------------
ECHO                                                         4.  DataScript
ECHO                                           -------------------------------------
ECHO                                                         5.  Login 
ECHO                                           -------------------------------------
ECHO                                                         6.  Patient
ECHO                                           -------------------------------------
ECHO                                                         7.  Case
ECHO                                           -------------------------------------
ECHO                                                         8.  Insurance
ECHO                                           -------------------------------------
ECHO                                                         9.  Notes
ECHO                                           -------------------------------------
ECHO                                                         10. Scheduler
ECHO                                           -------------------------------------
ECHO                                                         11. Apage 
ECHO                                           -------------------------------------
ECHO                                                         12. FLR
ECHO                                           -------------------------------------
ECHO                                                         13. Add Charges
ECHO                                           -------------------------------------
ECHO                                                         14. S-Page
ECHO                                           -------------------------------------
ECHO                                                         15. EMR
ECHO                                           -------------------------------------
ECHO                                                         16. Financial
ECHO                                           -------------------------------------
ECHO                                                         17. Task
ECHO                                           -------------------------------------
ECHO                                           -------------------------------------


ECHO                                           ==========PRESS 'Q' TO QUIT==========
ECHO.
echo.
echo.
SET INPUT=
SET /P INPUT=Please select a number:




echo ======Type  Browser Name that You Want to Execute For and then Press Enter================================
echo.
echo.
echo.
echo                         chrome
echo.
echo                         firefox
set /p input1=""
echo %input1%

IF /I '%INPUT%'=='1' GOTO Selection1
IF /I '%INPUT%'=='2' GOTO Selection2
IF /I '%INPUT%'=='3' GOTO Selection3
IF /I '%INPUT%'=='4' GOTO Selection4
IF /I '%INPUT%'=='5' GOTO Selection5
IF /I '%INPUT%'=='6' GOTO Selection6
IF /I '%INPUT%'=='7' GOTO Selection7
IF /I '%INPUT%'=='8' GOTO Selection8
IF /I '%INPUT%'=='9' GOTO Selection9
IF /I '%INPUT%'=='10' GOTO Selection10
IF /I '%INPUT%'=='11' GOTO Selection11
IF /I '%INPUT%'=='12' GOTO Selection12
IF /I '%INPUT%'=='13' GOTO Selection13
IF /I '%INPUT%'=='14' GOTO Selection14
IF /I '%INPUT%'=='15' GOTO Selection15
IF /I '%INPUT%'=='16' GOTO Selection16
IF /I '%INPUT%'=='17' GOTO Selection17
IF /I '%INPUT%'=='Q' GOTO Quit



ECHO                                             ============INVALID INPUT============
ECHO                                             -------------------------------------
ECHO                                             Please select a number from the Main
echo                                             Menu [1-17] or select 'Q' to quit.
ECHO                                             -------------------------------------
ECHO                                             ======PRESS ANY KEY TO CONTINUE======

PAUSE > NUL
GOTO MENU













:Selection1
echo =====================Executing Smoke==ON %input1%=======================
call mvn clean test -Drun=Smoke.xml -Dbrowser=%input1%
pause
exit

:Selection2
echo =====================Executing Sanity==ON %input1%=======================
call mvn clean test -Drun=Sanity.xml -Dbrowser=%input1%
pause
exit

:Selection3
echo =====================Executing CompleteRun==ON %input1%=======================
echo.
echo %CD%
pause
call mvn clean test -Drun=CompleteRun.xml -Dbrowser=%input1%
pause
exit

:Selection4
echo =====================Executing DataScript==ON %input1%=======================
echo.
call mvn clean test -Drun=DataScript.xml -Dbrowser=%input1%
pause
exit

:Selection5
echo =====================Executing Login Module==ON %input1%=======================
echo.
call mvn clean test -Drun=LoginModule.xml -Dbrowser=%input1%
pause
exit



:Selection6

echo.
echo =====================Executing Patient Module==ON %input1%=======================
call mvn clean test -Drun=PatientModule.xml -Dbrowser=%input1%
pause
exit

:Selection7

echo.
echo =====================Executing Case Module==ON %input1%=======================
call mvn clean test -Drun=CaseModule.xml -Dbrowser=%input1%
pause
exit

:Selection8
echo =====================Executing Insurance Module==ON %input1%=======================
echo.
call mvn clean test -Drun=InsuranceModule.xml -Dbrowser=%input1%
pause
exit

:Selection9

echo.
echo =====================Executing Notes Module ==ON %input1%=======================
call mvn clean test -Drun=NotesModule.xml -Dbrowser=%input1%
pause
exit

:Selection10
echo =====================Executing Scheduler Module On %input%=========================
call mvn clean test -Drun=SchedulerModule.xml -Dbrowser=%input1%
pause
exit

:Selection11

echo.
echo =====================Executing APageModule==ON %input1%=======================
call mvn clean test -Drun=APageModule.xml -Dbrowser=%input1%
pause
exit

:Selection12
echo =====================Executing FLRModule==ON %input1%=======================
echo.
call mvn clean test -Drun=FLRModule.xml -Dbrowser=%input1%
pause
exit

:Selection13
echo =====================Executing AddCharges Module ==ON %input1%=======================
echo.
call mvn clean test -Drun=AddChargesModule.xml -Dbrowser=%input1%
pause
exit

:Selection14

echo.
echo =====================Executing S-page Module==ON %input1%=======================
call mvn clean test -Drun=SPageModule.xml -Dbrowser=%input1%
pause
exit

:Selection15

echo.
echo =====================Executing EMR   ==ON %input1%=======================
call mvn clean test -Drun=EMR.xml -Dbrowser=%input1%
pause
exit

:Selection16

echo.
echo =====================Executing Financial ==ON %input1%=======================
call mvn clean test -Drun=Financial.xml -Dbrowser=%input1%
pause
exit

:Selection17
echo =====================Executing Task ==ON %input1%=======================
echo.
call mvn clean test -Drun=Task.xml -Dbrowser=%input1%
pause
exit

:Quit
CLS

ECHO ==============THANKYOU===============
ECHO -------------------------------------
ECHO ======PRESS ANY KEY TO CONTINUE======

PAUSE>NUL
EXIT