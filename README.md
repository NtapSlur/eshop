# Modul 2
## Refleksi
1. Pada saat proses code analysis menggunakan tool pilihan saya, yaitu PMD, terdapat satu code quality error yang terjadi, yaitu penamaan variabel yang tidak sesuai dengan kaidah yang seharusnya.
    ```
    The JUnit 5 test method name 'listProductTitle_isCorrect' doesn't match '[a-z][a-zA-Z0-9]*': 
   file:///home/runner/work/eshop/eshop/src/test/java/id/ac/ui/cs/advprog/eshop/functional/CreatePageFunctionalTest.java#L41
   Configurable naming conventions for method declarations. This rule reports
   method declarations which do not match the regex that applies to their
   specific kind (e.g. JUnit test or native method). Each regex can be
   configured through properties.
   
   By default this rule uses the standard Java naming convention (Camel case).
   
   MethodNamingConventions (Priority: 1, Ruleset: Code Style)
   https://docs.pmd-code.org/pmd-doc-7.0.0-rc4/pmd_rules_java_codestyle.html#methodnamingconventions
    ```
   Terdapat empat penamaan variabel yang membuat code quality error dari PMD, yaitu:
    - listProductTitle_isCorrect
    - createProduct_isCorrect
    - pageTitle_isCorrect
    - welcomeMessage_homepage_isCorrect
   
    Oleh karena itu, saya merename variabel-variabel tersebut sehingga memenuhi kaidah yang seharusnya dan menghilangkan code quality error tersebut.

2. Menurut saya, implementasi yang sudah dilakukan sudah memenuhi definisi CI/CD. Pertama, setiap kali muncul suatu commit yang sudah dipush ke branch yang diinginkan, GitHub akan melakukan Actions terhadap workflow yang terdapat pada branch tersebut, misalnya untuk kasus di module 2 ini adalah PMD dan CI. Khusus untuk branch main, terdapat tambahan workflow lagi yaitu scorecard dan untuk deployment via koyeb. Jika suatu branch di merge ke main, maka GitHub Actions akan memulai workflow lagi yang dimiliki oleh main sehingga setiap terjadi perubahan pada commit akan selalu dicek secara otomatis oleh GitHub Actions. Selain itu, untuk deployment dari web itu sendiri sudah diset agar dapat otomatis redeploy setiap terjadi perubahan pada branch main. Karena sudah banyak proses automation yang terjadi, maka menurut saya definisi CI/CD untuk module 2 ini sudah dipenuhi.

# Modul 1
## Refleksi 1
Dari kode yang sudah saya buat, menurut saya, ada beberapa clean code principles yang sudah saya terapkan:
- Meaningful Names
    - Untuk pemilihan nama variabel, saya sudah menggunakan nama variabel yang mudah dipahami dan menggambarkan apa kegunaan dari variabel tersebut
- Functions
    - Dalam pembuatan fungsi, saya mencoba untuk memastikan bahwa satu function hanya memiliki 1 fungsi saja
    - Untuk penamaan dalam fungsi saya sudah membuatnya sehingga lebih deskriptif dan secara tidak langsung menjelaskan apa kegunaan dari fungsi tersebut
- Comments
    - Dalam penulisan kode, saya sudah meminimalisir penggunaan comment karena kode yang ditulis sudah mencerminkan apa yang sedang dikerjakan dan tidak perlu untuk diberi comment tambahan lagi

Kemudian untuk konteks security, hal yang sudah saya implementasikan adalah dalam penulisan kode, perubahan sedikit apapun yang sudah dibuat akan langsung saya test sehingga mengantisipasi terjadi error pada saat akhir proses development

Dalam penulisan kode, saya membuat beberapa fungsi yang dapat mereturn null, di mana hal ini kurang sejalan dengan konsep dari clean code. Hal yang bisa diimprove adalah dengan mengubah implementasi logic dari fungsi tersebut sehingga tidak perlu mereturn nilai null.

## Refleksi 2
1. Setelah saya menulis unit test, saya mulai menyadari bahwa betapa pentingnya unit testing ini, karena kode yang telah dibuat dapat suatu saat berubah. Dengan dibuatnya unit testing, kita dapat memastikan bahwa kode kita dapat berjalan dengan aman walaupun adanya perubahan dari kode dan memastikan bahwa kode dapat memberikan output yang sesuai.

    Kemudian, untuk unit testing menurut saya dibuat secukupnya, tetapi mencakup semua fungsi yang ada di program kita sehingga cakupan fungsi yang dilakukan testing dapat seluas-luasnya sehingga meminimalisir penemuan bug. Menurut saya, code coverage yang 100% belum tentu bahwa di dalam kode yang sudah dibuat tidak terdapat bug, karena unit testing pada dasarnya dibuat oleh manusia dan bisa saja kita lupa dalam menghandle edge case dari suatu fungsi. Walaupun 100% code coverage belum tentu program kita tidak memiliki bug, tetapi setidaknya kita sudah meminimalisir penemuan bug di program kita 


2. Dalam kasus yang diberikan, kita ingin untuk melakukan testing terhadap jumlah dari item yang ada di product list. Menurut saya, kita tidak perlu untuk membuat sebuah test suite baru, karena hampir semua fungsi utama yang dilakukan untuk melakukan testing terhadap product sudah tercakup di CreateProductFunctionalTest, seperti untuk melakukan penambahan produk secara berkali-kali. Menurut saya, kita dapat memiliki 2 pilihan:
    - Tetap membuat sebuah test suite baru, tetapi fungsi-fungsi yang sudah dibuat sebelumnya di CreateProductFunctionalTest dapat diimport untuk mengurangi redudansi
    - Menambahkan fungsi testing baru di file CreateProductFunctionalTest untuk mengecek jumlah row yang ada di tabel pada halaman ProductList
