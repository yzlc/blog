Write-Host ""
$aria2Version = "1.34.0"
Write-Host "Installing Aria2 $aria2Version for Windows..."
$downloads = "$env:USERPROFILE\Downloads"
Function Unzip-File()
{
    param([string]$ZipFile, [string]$TargetFolder)
    #确保目标文件夹必须存在
    if (!(Test-Path $TargetFolder))
    {
        mkdir $TargetFolder
    }
    $shellApp = New-Object -ComObject Shell.Application
    $files = $shellApp.NameSpace($ZipFile).Items()
    $shellApp.NameSpace($TargetFolder).CopyHere($files)
}
Function installAria2()
{
    param([string]$version, [string]$targetFolder)
    $zipName = "aria2-$version-win-64bit-build1"
    $src = "https://github.com/aria2/aria2/releases/download/release-$version/$zipName.zip"
    $dest = "$targetFolder\$zipName.zip"
    $client = New-Object System.Net.WebClient
    if (!(Test-Path $dest))
    {
        $client.DownloadFile($src, $dest)
    }
    if (!(Test-Path $targetFolder\$zipName))
    {
        Unzip-File -ZipFile $dest -TargetFolder $targetFolder
    }
    (Get-Content $PSScriptRoot\..\aria2.conf).replace('[dir]',$downloads) | Set-Content $PSScriptRoot\..\aria2.conf
    copy $targetFolder\$zipName\aria2c.exe $PSScriptRoot\..
    rm $dest,$targetFolder\$zipName -Recurse
}
Get-Process aria2c -ErrorAction "SilentlyContinue"
If (!$?)
{
    installAria2 -version $aria2Version -targetFolder $PSScriptRoot
}