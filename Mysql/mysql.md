## mysql

## 一、mysql安装

1、官网下载mysql压缩包https://dev.mysql.com/downloads/mysql/

2、解压到指定文件比如C:\Program Files\mysql-8.0.15-winx64

3、配置环境变量Path,添加C:\Program Files\mysql-8.0.15-winx64\bin到Path

4、配置初始化的my.ini文件

​	[mysqld]

​	# 设置3306端口

​	port=3306

​	# 设置mysql的安装目录

​	basedir=C:\\Program Files\\mysql-8.0.15-winx64  # 切记此处一定要用双斜杠\\，单斜杠我这里会出错，不过看别人的教程，有的是单斜杠。自己尝试吧

​	# 设置mysql数据库的数据的存放目录

​	#datadir=C:\\Program Files\\mysql-8.0.15-winx64\\mysqlData   # 此处同上

​	# 允许最大连接数

​	max_connections=200

​	# 允许连接失败的次数。这是为了防止有人从该主机试图攻击数据库系统

​	max_connect_errors=10

​	# 服务端使用的字符集默认为UTF8

​	character-set-server=utf8

​	# 创建新表时将使用的默认存储引擎

​	default-storage-engine=INNODB

​	# 默认使用“mysql_native_password”插件认证

​	default_authentication_plugin=mysql_native_password

​	[mysql]

​	# 设置mysql客户端默认字符集

​	default-character-set=utf8

​	[client]

​	# 设置mysql客户端连接服务端时默认使用的端口

​	port=3306

​	default-character-set=utf8

5、以管理员模式运行cmd，执行  mysqld --initialize --console  命令，执行完成后，会打印 root 用户的初始默认密码，比如

C:\Users\Administrator>cd C:\Program Files\MySQL\bin

C:\Program Files\MySQL\bin>mysqld --initialize --console

2018-04-28T15:57:17.087519Z 0 [System] [MY-013169] [Server] C:\Program Files\MySQL\bin\mysqld.exe (mysqld 8.0.11) initializing of server in progress as process 4984

2018-04-28T15:57:24.859249Z 5 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: rI5rvf5x5G,E

2018-04-28T15:57:27.106660Z 0 [System] [MY-013170] [Server] C:\Program Files\MySQL\bin\mysqld.exe (mysqld 8.0.11) initializing of server has completed

C:\Program Files\MySQL\bin>

6、安装服务 

​	如果没有配置环境变量需要在mysql解压目录下的bin目录管理员模式执行cmd命令 mysqld --install

​	安装完成后再启动mysql服务 执行命令 net start mysql 启动服务（net stop mysql停止服务）

​	

​	sc delete MySQL/mysqld -remove 卸载mysql服务

​	

7、更改密码（密码在安装mysql时候打印的那个）

​	cmd执行命令 mysql -u root -prI5rvf5x5G

​	

​	在MySQL中执行命令：

​	ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '新密码';  

## 二、mysql卸载

1》停止MySQL服务

开始-》所有应用-》Windows管理工具-》服务，将MySQL服务停止。

2》卸载mysql server

控制面板\所有控制面板项\程序和功能，将mysql server卸载掉。

3》将MySQL安装目录下的MySQL文件夹删除（我的安装目录是C:\Program Files (x86)\MySQL）

4》运行“regedit”文件，打开注册表。

删除HKEY_LOCAL_MACHINE\SYSTEM\ControlSet001\Services\Eventlog\Application\MySQL文件夹

删除HKEY_LOCAL_MACHINE\SYSTEM\ControlSet002\Services\Eventlog\Application\MySQL文件夹。

删除HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\Eventlog\Application\MySQL的文件夹。

如果没有相应的文件夹，就不用删除了。

5》删除C盘下的“C:\ProgramData\MySQL ”文件夹，如果删除不了则用360粉碎掉即可，

该programData文件默认是隐藏的，设置显示后即可见，或者直接复制 C:\ProgramData 到地址栏回车即可进入！

将整个MySQL文件夹删除掉。。。

6》开始-》所有应用-》Windows管理工具-》服务

如果已经将MySQL卸载，但通过“开始-》所有应用-》Windows管理工具-》服务”查看到MySQL服务仍然残留在

系统服务里。又不想改服务名，改怎么办呢。

只要在CMD里输入一条命令就可以将服务删除：

sc delete mysql //这里的mysql是你要删除的服务名

这样一来服务就被删除了。

然后，就可以重装MySQL数据库了！！