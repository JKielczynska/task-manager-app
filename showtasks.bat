call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrowser
echo.
echo runcrud.bat has errors
goto fail

:openbrowser
start http://localhost:8080/crud/v1/tasks
if "%ERRORLEVEL%" == "0" goto end
echo.
echo The browser cannot be opened.
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.