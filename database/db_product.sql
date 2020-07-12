use db_product;

select count(*) from Product p WHERE p.id_category = 11

select @@GLOBAL.time_zone
SET GLOBAL time_zone = '+7:00';

select * from login;
select * from produk;
desc produk;
desc kategori;

create table login (
id_user int not null primary key AUTO_INCREMENT,
email varchar(225) not null,
username varchar(225) not null UNIQUE,
password varchar(20) not null);

insert into login (id_user, email, username, password) values
(1, 'erna9470@gmail.com', 'erna', 'abc123'),
(2, 'nadachai1811@gmail.com', 'nada', 'abc1234'),
(3, 'zalita284@gmail.com', 'zalita', 'abc45'),
(4, 'pritafauziah@gmail.com', 'prita', 'abc1'),
(5, 'ullyanadda@gmail.com', 'ullya', 'abc12');

create table kategori ( 
id_kategori int not null PRIMARY KEY AUTO_INCREMENT, 
nama_kategori varchar(225) not null, 
parent_id int not null);

create table produk (
id_produk int not null PRIMARY KEY AUTO_INCREMENT,
id_kategori int not null,
nama_produk varchar(225) not null,
deskripsi_produk text,
harga_produk decimal not null,
image_link varchar(225) not null,
merk varchar(225) not null,
warna varchar(225) not null,
bahan varchar(225) not null,
foreign key (id_kategori) references kategori(id_kategori));


insert into kategori (id_kategori, nama_kategori, parent_id) values
(1, 'fashion wanita', 0),                                         
(11, 'atasan', 1),
(12, 'celana', 1),
(13, 'tas', 1),
(14, 'sepatu', 1),
(111, 'kemeja', 11),
(112, 'tanktop', 11),
(113, 'kaos', 11),
(121, 'celana panjang', 12),
(122, 'celana pendek', 12),
(123, 'celana jeans', 12),
(131, 'hand bag', 13),
(132, 'selempang', 13),
(133, 'back pack', 13),
(141, 'sneakers', 14),
(142, 'heels', 14),
(143, 'flat shoes', 14);

insert into produk (id_produk, id_kategori, nama_produk, deskripsi_produk, harga_produk, image_link, merk, warna, bahan) values
(1, 111, 'Kemeja motif', 'Bahan Flanel | LD : 94cm | P : 51cm', 599900, 'https://static.zara.net/photos///2020/V/0/1/p/2479/209/330/2/w/1538/2479209330_6_1_1.jpg?ts=1582028993240’, ‘zara’, ‘hijau corak’, ‘katun’),
(2, 111, ‘Blus motif hewan’, ‘size xs, s, m, l. Bahan: katun’, 599900, ‘https://static.zara.net/photos///2020/V/0/1/p/7484/030/064/2/w/1538/7484030064_6_1_1.jpg?ts=1582029193134','zara', 'hitam', 'satin'),
(3, 111, 'Blus kerah mandarin hitam', 'size xs, s, m, l. Bahan: katun', 499900, 'https://static.zara.net/photos///2020/V/0/1/p/8004/021/250/2/w/1256/8004021250_2_1_1.jpg?ts=1582275370968', 'zara', 'hitam', 'katun'),
(4, 111, 'Kemeja Popelin dengan rimpel', 'size xs, s, m, l. Bahan: katun', 399900, 'https://static.zara.net/photos///2020/V/0/1/p/8004/021/250/2/w/1256/8004021250_2_1_1.jpg?ts=1582275370968', 'zara', 'putih', 'katun'),
(5, 111, 'Kemeja popelin bordiran', 'size xs, s, m, l Bahan: katun', 479900, 'https://static.zara.net/photos///2020/V/0/1/p/7200/024/250/2/w/1538/7200024250_2_1_1.jpg?ts=1582029192947', 'h&m', 'putih', 'katun'),
(6, 111, 'Blus bahan satin', 'size s, m, l Bahan: katun', 699900, 'https://static.zara.net/photos///2020/V/0/1/p/3666/028/500/2/w/1538/3666028500_6_1_1.jpg?ts=1582029104093', 'h&m', 'hijau', 'katun'),
(7, 111, 'Blus linen', 'size s, m, l, xl Bahan: linen', 400900, 'https://static.zara.net/photos//mkt/misc/watermarks/joinlife-ss19-v2/joinlife-large.svg?ts=1571917268523', 'h&m', 'crom', 'satin'),
(8, 111, 'kemeja lengan pof', 'size s, m, l, xl Bahan: jatuh', 599902, 'https://static.zara.net/photos///2020/V/0/1/p/3067/005/727/2/w/1538/3067005727_1_1_1.jpg?ts=1578653093705', 'zara', 'crom', 'serat selulosa'),
(9, 111, 'kemeja kancing', 'size xs, m, l, xl Bahan: jatuh', 699900, 'https://static.zara.net/photos///2020/V/0/1/p/4437/053/711/2/w/1538/4437053711_6_1_1.jpg?ts=1581004582161', 'h&m', 'crom', 'serat selulosa'),
(10, 111, 'kemeja oversize', 'size xs, m, l Bahan: jatuh', 599903, 'https://static.zara.net/photos///2020/V/0/1/p/3067/016/712/2/w/1656/3067016712_1_1_1.jpg?ts=1582737339565', 'h&m', 'putih', 'serat selulosa'),
(11, 111, 'kemeja denim', 'size xs, m, l Bahan: denim', 479900, 'https://static.zara.net/photos///2020/V/0/1/p/7986/021/800/2/w/1656/7986021800_6_1_1.jpg?ts=1580468194093', 'pull&bear' , 'hitam', 'denim'),
(12, 111, 'kemeja denim biru', 'size xs,s m, l, xl Bahan: denim', 479900, 'https://static.zara.net/photos///2020/V/0/1/p/8197/020/400/2/w/1656/8197020400_6_1_1.jpg?ts=1579873275261', 'pull&bear', 'biru', 'denim'),
(13, 111, 'kemeja biru', 'size xs,s m, l, xl Bahan: katun', 549900, 'https://static.zara.net/photos///2020/V/0/1/p/7147/022/427/2/w/1450/7147022427_1_1_1.jpg?ts=1583412226410', 'pull&bear', 'biru', 'katun'),
(14, 111, 'kemeja biru p&b', 'size xs,s m, l, xl Bahan: serat selulosa', 599905, 'https://static.zara.net/photos//mkt/misc/watermarks/joinlife-ss19-v2/joinlife-large.svg?ts=1571917268523', 'stadivarius', 'biru', 'serat selulosa'),
(15, 111, 'kemeja motif macan', 'size xs,s m, l, xl Bahan: poliester', 479900, 'https://static.zara.net/photos///2020/V/0/1/p/2100/201/051/2/w/1450/2100201051_6_1_1.jpg?ts=1577720984504', 'pull&bear', 'motif corak', 'poliester'),
(16, 111, 'kemaja motif kotak', 'size xs,s m, l, xl Bahan: poliester', 699900, 'https://static.zara.net/photos///2020/V/0/1/p/2538/151/605/2/w/1450/2538151605_6_1_1.jpg?ts=1581670578994', 'stadivarius','motif corak', 'poliester'),
(17, 111, 'kemeja piyama motif', 'size xs,s m, l, xl Bahan: poliester', 599905, 'https://static.zara.net/photos///2020/V/0/1/p/2581/665/056/2/w/1450/2581665056_6_1_1.jpg?ts=1583768912316', 'pull&bear', 'motif corak', 'poliester'),
(18, 111, 'kemeja pink poplyn oversize', 'size xs,s m, l, xl Bahan: katun', 479900, 'https://static.zara.net/photos///2020/V/0/1/p/3067/045/622/2/w/1450/3067045622_6_1_1.jpg?ts=1583400231251', 'pull&bear', 'pink', 'katun'),
(19, 111, 'kemeja pink linen crop', 'size xs,s m, l, xl Bahan: denim', 599900,'https://static.zara.net/photos//mkt/misc/watermarks/joinlife-ss19-v2/joinlife-large.svg?ts=1571917268523', 'stadivarius', 'pink', 'linen'),
(20, 111, 'kemeja hijau linen crop', 'size xs,s m, l, xl Bahan: linen', 479900, 'https://static.zara.net/photos///2020/V/0/1/p/2361/500/506/2/w/1450/2361500506_6_1_1.jpg?ts=1583846464947', 'stadivarius', 'hijau', 'linen'),
(21, 112, 'kaos mulan', 'size xs,s m, l, xl', 399900, 'https://static.zara.net/photos//mkt/misc/watermarks/joinlife-ss19-v2/joinlife-large.svg?ts=1571917268523', 'zara', 'putih', 'katun'),
(22, 112, 'kaos polos kuning', 'size xs,s m, l, xl', 299900, 'https://static.zara.net/photos///2020/V/0/1/p/4174/156/300/2/w/1450/4174156300_2_1_1.jpg?ts=1580382682814', 'zara', 'kuning', 'katun'),
(23, 112, 'kaos orane', 'size xs,s m, l, xl', 299900, 'https://static.zara.net/photos///2020/V/0/1/p/1443/036/615/2/w/1450/1443036615_6_1_1.jpg?ts=1583399692255', 'mango','orange', 'katun'),
(24, 112, 'kaos polos hijau', 'size xs,s m, l, xl', 299900, 'https://static.zara.net/photos//mkt/misc/watermarks/joinlife-ss19-v2/joinlife-large.svg?ts=1571917268523', 'mango', 'hijau', 'katun'),
(25, 112, 'kaos pink basic', 'size xs,s m, l, xl', 299900, 'https://static.zara.net/photos//mkt/misc/watermarks/joinlife-ss19-v2/joinlife-large.svg?ts=1571917268523', 'mango', 'pink', 'katun'),
(26, 112, 'kaos motif belang-belang', 'size xs,s m, l, xl', 139900, 'https://static.zara.net/photos///2020/V/0/1/p/0264/337/025/2/w/1450/0264337025_1_1_1.jpg?ts=1583927841818', 'stadivarius', 'orange', 'katun'),
(27, 112, 'kaos kerah hitam', 'size xs,s m, l, xl', 199900, 'https://static.zara.net/photos//mkt/misc/watermarks/joinlife-ss19-v2/joinlife-large.svg?ts=1571917268523', 'h&m', 'hitam', 'katun'),
(28, 112, 'kaos simpul ungu', 'size xs,s m, l, xl', 299900, 'https://static.zara.net/photos///2020/V/0/1/p/0264/316/612/2/w/1450/0264316612_2_5_1.jpg?ts=1583855678784', 'pull&bear', 'ungu', 'viscose'),
(29, 112, 'kaos ungu', 'size xs,s m, l, xl', 139900, 'https://static.zara.net/photos//mkt/misc/watermarks/joinlife-ss19-v2/joinlife-large.svg?ts=1571917268523', 'zara', 'ungu', 'katun'),



