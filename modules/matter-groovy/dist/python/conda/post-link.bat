@echo off
(
  REM Uninstall Matter notebook extension
  "%PREFIX%\Scripts\matter_kernel_groovy.exe" "install"
) >>"%PREFIX%\.messages.txt" 2>&1