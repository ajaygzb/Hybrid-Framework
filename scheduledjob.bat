@echo off
echo.
echo.
d:
cd D:\BMS_SVN
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
echo                         AddChargesModule
echo.
echo                         SPageModule
echo.
echo                         EMR
echo.
echo                         Financial
echo.
echo                         Task
echo.
echo                         PQRS
echo.
echo                         TestError

echo .........................Starting Execution For %input% On Browser %input1% ............
echo ========================================================================Automation Started=========================================
echo. 
echo.
echo.
call mvn clean test -Drun=CompleteRun.xml -Dbrowser=chrome
echo.
echo.
echo.
pause
echo ========================================================================Automation Ends=========================================
echo.
echo.
echo =============================%input% Execution Completed There You Go! Open Automation Reports Folder on desktop with build version no. and Module name....
echo.
echo.
echo.
echo.
echo==============================================================THANK YOU================================================================================

pause