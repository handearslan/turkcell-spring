**#Pair4**
#Hande Arslan
#Halef Budanur
#Fuat Hüriyetoğlu
#Baran Büyük
#Ahmet Çetiner
#Elif Nida Karakaş
#Yavuz Selim Özbey 

Uygulamanızın aşağıdaki işlevleri yerine getirebiliyor olması beklenmektedir. (Bu işlevler halihazırda bulunuyor olabilir, olmayanları kodlayınız olanları kontrol ediniz düzgün çalışıp çalışmadığı noktasında)

*Uygulamanızda kategoriler; eklenebilmeli, güncellenebilmeli, silinebilmeli ve listelenebilmelidir.
*Uygulamanızda ürünler; eklenebilmeli, güncellenebilmeli, silinebilmeli ve listelenebilmelidir.

*_Ürün eklerken ve güncellerken aşağıdaki kurallar uygulanmalıdır.
Ürün ismi 3 haneden kısa olamaz. bu tamam
**Supplier id ve category id boş geçilemez ve 0'dan küçük eşit olamaz**  
**Unit Price boş geçilemez ve 0'dan küçük olamaz**
Stock bilgisi 0dan küçük olamaz
Birebir aynı **isimde ikinci ürün eklenemez.**_



*Uygulamanızda sipariş modülü eklenmeli ve aşağıdaki gibi çalışmalıdır;
*Sipariş eklenebilmelidir ve sipariş eklenirken gönderilen ürünler order_details tablosunda tutulmalıdır.
*Sipariş eklenirken aşağıdaki kurallar uygulanmalıdır.
Customer_ID boş geçilemez ve verilen ID ile db'de bir Customer olması zorunludur. +
Employee_ID boş geçilemez ve verilen ID ile db'de bir Employee olması zorunludur. +
Order Date gönderilmemeli kod kısmında otomatik olarak o anın tarihine set edilmelidir. +
Required Date kullanıcı tarafından gönderilmeli ve o günün tarihinden daha geçmiş bir tarih gönderilmemelidir.
Shipped Date başlangıçta null geçilmelidir.
Order tablosu için diğer alanlar için bir kural/validation uygulama zorunluluğunuz yoktur.

*Sipariş eklenirken request içerisine aynı zamanda ürünler adetleri ile birlikte gönderilmelidir.
Ürün adeti 0'dan küçük eşit olamaz. +
Buradaki ürünün unit_price'ı ürün idsi kullanılarak ürünler tablosundan çekilmelidir.
Bu ürünler order_details'e atılmalı order_id olarak yeni eklenen order'ın idsi ve diğer alanlara üründen gelen bilgiler girilmelidir (discount'ı 0 alınız)
Eğer ilgili üründen talep edilen adet kadar yoksa adet verilebilecek max. stok olarak güncellenmelidir. örn: 5 adet X ürünü istenmiş stokta 3 adet varsa bu tabloya 3 adet yazılmalı.
Siparişe eklenen her ürünün stok adeti quantity kadar azaltılmalıdır.


Siparişler içerisindeki ürünler ve bu ürünlerin isimleriyle birlikte listelenebilmelidir (iki adet join order,order_details,product)
