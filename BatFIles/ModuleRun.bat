@echo off
echo.
cd..
echo %cd%
echo.
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
echo                         AddChargesModule
echo.
echo                         SPageModule
echo.
echo                         EMR
echo.
echo.                        Financial          
echo.
echo.                        Task
echo.
echo.                        PQRS
echo ============================================================================
echo.
echo.
echo ====================After Automation Run Output reports can be seen inside Automation Reports Folder on desktop with build version no.================
echo.
echo.
echo.
echo.
echo.
echo.
call mvn clean test -Drun=LoginModule.xml -Dbrowser=chrome
echo.
call mvn clean test -Drun=PatientModule.xml -Dbrowser=chrome
echo.
call mvn clean test -Drun=CaseModule.xml -Dbrowser=chrome
echo.
call mvn clean test -Drun=InsuranceModule.xml -Dbrowser=chrome
echo.
call mvn clean test -Drun=NotesModule.xml -Dbrowser=chrome
echo.
call mvn clean test -Drun=SchedulerModule.xml -Dbrowser=chrome
echo.
call mvn clean test -Drun=APageModule.xml -Dbrowser=chrome
echo.
call mvn clean test -Drun=FLRModule.xml -Dbrowser=chrome
echo.
call mvn clean test -Drun=AddChargesModule.xml -Dbrowser=chrome
echo.
call mvn clean test -Drun=SPageModule.xml -Dbrowser=chrome
echo.
call mvn clean test -Drun=EMR.xml -Dbrowser=chrome
echo.
call mvn clean test -Drun=Financial.xml -Dbrowser=chrome
echo.
echo.
call mvn clean test -Drun=Task.xml -Dbrowser=chrome
echo.
echo.
call mvn clean test -Drun=PQRS.xml -Dbrowser=chrome
echo.
echo.
call mvn clean test -Drun=smoke.xml -Dbrowser=chrome
echo.
echo.
call mvn clean test -Drun=Sanity.xml -Dbrowser=chrome
echo.
echo.
call mvn clean test -Drun=DataScript.xml -Dbrowser=chrome
echo.
echo.
echo.
echo.
pause
echo ========================================================================Automation Ends=========================================
echo.
echo.
echo =============================All Module Execution Completed There You Go! Open Automation Reports Folder on desktop with build version no. and Module name....
echo.
echo.
echo.
echo.
echo==============================================================THANK YOU================================================================================

pause