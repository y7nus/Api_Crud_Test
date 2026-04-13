# PetStore Dinamik CRUD Otomasyonu

Bu senaryo, ID'yi API'den dinamik olarak alıp tüm süreci onun üzerinden yönetir.

## Uçtan Uca Pet Yaşam Döngüsü
* Pet için temel ayarları yap

* Pet oluştur ve gerçek ID'yi sakla
* status code "200" olmalı

* Oluşturulan ID ile peti getir
* status code "200" olmalı
* "name" contains "Karabas"
* "status" contains "available"

* Pet bilgilerini güncelle
* status code "200" olmalı

* Oluşturulan ID ile peti getir
* "name" contains "Karabas Updated"
* "status" contains "sold"

* Peti sistemden sil
* status code "200" olmalı

* Pet'in silindiğini doğrula