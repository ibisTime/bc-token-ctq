
#!/usr/bin/expect

set tomcat_path "/mnt/www/coin/tomcat_xn_ctq"
set war_name "bc-ctq"
set host_ip "47.90.102.163"

spawn ssh root@${host_ip}
expect "*password:"
send "Bei123coin\r"
#关闭tomcat
expect "*#"
send "${tomcat_path}/bin/shutdown.sh\r"

expect "*#"
send "rm -rf ${tomcat_path}/webapps/${war_name}\r"

expect "*#"
send "rm -rf ${tomcat_path}/webapps/c${war_name}.war\r"

expect "*#"
send "exit\r"

#2.传输文件
spawn scp /Users/haiqingzheng/Desktop/${war_name}.war root@${host_ip}:${tomcat_path}/webapps
expect "*password:"
send "Bei123coin\r"
set timeout 300
send "exit\r"
expect eof

#3.重启tomcat
spawn ssh root@${host_ip}
expect "*password:"
send "Bei123coin\r"
expect "*#"
send "${tomcat_path}/bin/startup.sh\r"

expect "*#"
send "exit\r"
#interact





