@echo off
echo.
echo.
cd..
echo %cd%
echo.
echo.
echo ================WELCOME To BMS Automation=============================
echo ======================================================================
echo ======================================================================
echo Enter Which Module you want to test from List Below (Case sensitive)?
echo ======================================================================
echo.
echo.
echo.
echo                         Smoke
echo.
echo                         Sanity
echo.
echo                         CompleteRun
echo.
echo                         DataScript
echo.
echo                         LoginModule
echo.
echo                         PatientModule
echo.
echo                         CaseModule
echo.
echo                         InsuranceModule
echo.
echo                         NotesModule
echo.
echo                         SchedulerModule
echo.
echo                         APageModule
echo.
echo                         FLRModule
echo.
echo.
echo ============================================================================
echo.
echo.
echo ====================After Automation Run Output reports can be seen inside Automation Reports Folder on desktop with build version no.================
echo.
pause
echo.
echo.
echo ======Type Module Name that You Want to Execute and then Press Enter================================
set /p input=""
echo %input%
echo.
echo.
echo.
echo ======Type  Browser Name that You Want to Execute For and then Press Enter================================
echo.
echo.
echo.
echo                         chrome
echo.
echo                         firefox
set /p input1=""
echo %input1%
cd %cd%
echo .........................Starting Execution For %input% On Browser %input1% ............
echo ========================================================================Automation Started=========================================
echo. 
echo.
echo.
call mvn clean test -Drun=%input%.xml -Dbrowser=%input1% --log-file Automation_LOGS_%input%_%date%.txt
echo.
echo.
pause
echo ========================================================================Automation Ends=========================================
echo.
echo.
echo =============================   %input%   Execution Completed There You Go! Open Automation Reports Folder on desktop with build version no. and Module name....
echo.
echo.
echo.
echo.
echo==============================================================THANK YOU================================================================================

pause