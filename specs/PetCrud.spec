# PetStore Dinamik CRUD Otomasyonu

Bu senaryo, ID'yi API'den dinamik olarak alıp tüm süreci onun üzerinden yönetir.

## Pet Oluşturma
* Pet için temel ayarları yap
* Pet oluştur ve gerçek ID'yi sakla
* status code "200" olmalı

## Pet oluşturma ve oluşturulan peti getirme
* Pet için temel ayarları yap
* Pet oluştur ve gerçek ID'yi sakla
* status code "200" olmalı
* Oluşturulan ID ile peti getir
* status code "200" olmalı
* "name" contains "Karabas"
* "status" contains "available"

## Pet oluşturma, getirme ve güncellenen peti getirme
* Pet için temel ayarları yap
* Pet oluştur ve gerçek ID'yi sakla
* status code "200" olmalı
* Oluşturulan ID ile peti getir

* Pet bilgilerini güncelle

* Oluşturulan ID ile peti getir
* status code "200" olmalı
* "name" contains "Karabas Updated"
* "status" contains "sold"

## Pet oluşturma, getirme ve oluşturulan peti silme
* Pet için temel ayarları yap
* Pet oluştur ve gerçek ID'yi sakla
* status code "200" olmalı
* Oluşturulan ID ile peti getir
* status code "200" olmalı

* Peti sistemden sil
* status code "200" olmalı

* Pet'in silindiğini doğrula