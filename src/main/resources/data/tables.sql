# Tables creation 
create table ratings ( 
   userid int, 
   itemid int, 
   rating int,
   primary key (userid, itemid)); 

create table items ( 
   itemid int primary key, 
   title text, 
   videodate text, 
   imdb text, 
   action boolean, 
   adventure boolean, 
   animation boolean, 
   childrens boolean, 
   comedy boolean, 
   crime boolean, 
   documentary boolean, 
   drama boolean, 
   fantasy boolean, 
   noir boolean, 
   horror boolean, 
   musical boolean, 
   mystery boolean, 
   romance boolean, 
   scifi boolean, 
   thriller boolean, 
   war boolean, 
   western boolean); 
 
create table users ( 
   userid int primary key, 
   age int, 
   gender char, 
   occupation text, 
   zip varchar); 
 
# Indexes creation 
create index usersdata_index on ratings (userid); 
create index itemsdata_index on ratings (itemid);