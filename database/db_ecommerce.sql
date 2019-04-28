use db_ecommerce;
use db_product

select * from category;
drop table category;
drop table user_rating;
drop table product;
select * from tbl_user;
select * from penjual;
delete from tbl_user;
delete from penjual;
delete from product;
desc product;

create table category (id_category int not null PRIMARY KEY AUTO_INCREMENT, nama_category varchar(225) not null, parent_id int not null);

create table penjual (
id_penjual int not null PRIMARY KEY AUTO_INCREMENT, 
nama_penjual varchar(225), 
lokasi_penjual varchar(225), 
tanggal_jual date,
badge_penjual int not null);

create table product (
id_product int not null PRIMARY KEY AUTO_INCREMENT, 
id_category int not null, 
nama_product varchar(225) not null, 
deskripsi_product text, 
harga_product decimal not null,  
id_penjual int not null, 
image_link varchar(225) not null,
foreign key (id_category) references category(id_category), 
foreign key (id_penjual) references penjual(id_penjual));


create table badge (
id_badge int not null, 
nama_badge varchar(225), 
image varchar(225), 
primary key(id_badge));

create table tbl_user (
id_user int not null primary key AUTO_INCREMENT, 
email varchar(225) not null, 
nama_depan varchar(225) not null, 
nama_belakang varchar(225) not null, 
alamat text not null, 
kota varchar(225) not null, 
provinsi varchar(225) not null, 
kode_pos varchar(30) not null);

create table user_rating (
id_user int not null, 
id_product int not null, 
rating int not null primary key AUTO_INCREMENT,
foreign key (id_user) references tbl_user(id_user),
foreign key (id_product) references product(id_product));

create table rekomendasi (
id_user int not null, 
id_product int not null, 
id_category int not null, 
foreign key (id_user) references tbl_user(id_user),
foreign key(id_product) references product(id_product), 
foreign key(id_category) references category(id_category));

insert into category (id_category, nama_category, parent_id) values 
(1, 'fashion wanita', 0),
(2, 'fashion pria', 0);

insert into tbl_user (id_user, email, nama_depan, nama_belakang, alamat, kota, provinsi, kode_pos) values
(1, 'erna9470@gmail.com', 'erna', 'wati', 'jl.akses ui', 'depok', 'jawa barat', '12345'),
(2, 'nadachai1811@gmail.com', 'nada', 'charnunisa', 'jl.abdurahman', 'cibubur', 'jakarta timur', '13920'),
(3, 'zalita284@gmail.com', 'zalita', 'nadia', 'jl.janger 2', 'depok 2', 'jawa barat', '16411'),
(4, 'pritafauziah@gmail.com', 'prita', 'fauziah', 'jl.akses ui', 'depok', 'jawa barat', '12345'),
(5, 'ullyanadda@gmail.com', 'ullya', 'nada', 'jl.condet', 'cibubur', 'jakarta timur', '13560');


insert into penjual (id_penjual, nama_penjual, lokasi_penjual, tanggal_jual, badge_penjual) values
(111, 'gelsa', 'Depok', NOW(), 1),
(112, 'bella', 'Jakarta barat', NOW(), 1),
(113, 'alib', 'Depok', NOW(), 1),
(114, 'prafikasari', 'jakarta timur', NOW(), 1),
(115, 'hendra', 'jakarta selatan', NOW(), 2),
(116, 'rijal', 'jakarta selatan', NOW(), 2),
(117, 'yasa', 'Depok', NOW(), 2),
(118, 'andre', 'jakarta barat', NOW(), 2);

insert into user_rating (id_user, id_product, rating) values
(1, 1, 5),
(2, 2, 5),
(3, 3, 4),
(4, 4, 4),
(5, 5, 4);


insert into product (id_product, id_category, nama_product, deskripsi_product, harga_product, id_penjual, image_link) values 
(1, 1, 'Kemeja Flanel Pink', 'Bahan Flanel | LD : 94cm | P : 51cm', 69500, 111, 'https://www.tokopedia.com/pikoid/471-kemeja-flanel-pink-flanel-ikat-flanel-murah-kemeja-navy?trkid=f=Ca1770L000P0W0S0Sh00Co0Po0Fr0Cb0_src=directory_page=1_ob=24_q=_po=9_catid=1770');

