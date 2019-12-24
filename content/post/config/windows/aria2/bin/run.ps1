if (!([Security.Principal.WindowsPrincipal][Security.Principal.WindowsIdentity]::GetCurrent()).IsInRole([Security.Principal.WindowsBuiltInRole]"Administrator"))
{
    Start-Process powershell.exe "-NoProfile -ExecutionPolicy Bypass -File `"$PSCommandPath`"" -Verb RunAs; exit
}
. $PSScriptRoot\install.ps1
cd $PSScriptRoot
# -----------------------------------------------------------------------------
Write-Host ""
Write-Host "run..."
$Shell = New-Object -ComObject ("WScript.Shell")
$Shortcut = $Shell.CreateShortcut("C:\ProgramData\Microsoft\Windows\Start Menu\Programs\StartUp\aria2.lnk")
$Shortcut.TargetPath = "$PSScriptRoot\aria2.vbs"
$Shortcut.WorkingDirectory = "$PSScriptRoot\"
$Shortcut.Save()
# -----------------------------------------------------------------------------
wscript.exe aria2.vbs