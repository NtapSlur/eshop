# Modul 4
## Refleksi
1. Menurut saya prosedur TDD dapat membuat kode yang lebih teorganisir dan aman, karena disini kita membuat test case terlebih dahulu baru kodenya. Hal ini dapat menjamin kebenaran kode karena sudah diperkirakan apa output dari program sehingga jika terdapat sebuah kesalahan dapat langsung terdeteksi.
2. Menurut saya, test case yang dibuat sudah memenuhi prinsip F.I.R.S.T yang di mana sudah dibuat test yang menguji keseluruhan kode tetapi lebih minimal. Kemudian, tiap unit test juga independen sehingga tidak mempengaruhi hasil tes yang lain. 

# Modul 3
## Refleksi
1. S.O.L.I.D principles yang saya terapkan dalam module ini adalah:
    - Single Responsibility Principle
        - Pada class Controller, pada before-solid untuk ProductController dan CarController tergabung ke dalam 1 file dan untuk HomeController belum dipisah menjadi satu file baru. Oleh karena itu, pada after-solid saya memisahkan ketiga Controller tersebut menjadi file-file yang berbeda sehingga satu file Program hanya memegang satu class saja dan hanya berfungsi sebagai controller untuk mapping path pada website.
    - Interface Segregation Principle
        - Pada package service, baik before-solid maupun after-solid sudah menerapkan Interface Segregation Principle. Hal ini dapat terlihat dari terpisahnya interface class untuk CarService dan ProductService. Pemisahan ini ditujukan agar tidak ada method yang tidak relevan pada satu class dan berguna untuk mengimplementasikan method yang memang seharusnya diimplementasi pada kelas itu.  
    - Dependency Inversion Principle
        - Pada package controller, baik before-solid maupun after-solid sudah menerapkan Dependency Inversion Principle. Hal ini dapat terlihat dari CarController dan ProductController yang memakai AutoWired kepada CarService/ProductService dan bukan class implementasi dari CarService/ProductService. Hal ini bertujuan agar ketika terjadinya perubahan dari implementasi service dapat meminimalisir bug karena controller bergantung dari interface classnya dan bukan bergantung dengan implementasinya.
2. Ada beberapa manfaat dari pengaplikasian prinsip S.O.L.I.D dalam program yang dibuat:
    - Lebih mudah untuk memodifikasi suatu class karena sudah meminimalisir dependensi
        - Salah satu principle dari S.O.L.I.D adalah Dependency Inversion, yang dimana mengharuskan kita untuk bergantung kepada interface atau abstract class saja daripada class yang sudah mengimplementasikan interface/abstract yang ada. Hal ini sangat penting karena misalkan terdapat dua class yang mengimplements interface yang sama, maka kedua class ini tidak akan mempengaruhi dependensi dari method yang ada pada interface tersebut karena nantinya method dari kedua class tersebut akan diimplement masing-masing pada class implementasinya. Contoh dari pemanfaatan hal ini adalah penggunaan class CarService/ProductService sebagai tipe data dari controller dan bukan class implementasinya. 
    - Lebih mudah untuk dimaintain
        - Salah satu princple dari S.O.L.I.D adalah Open-Closed Principle yang memperbolehkan kita untuk menambahkan fitur dari sebuah class (mengextend fitur dari class) tetapi tidak memperbolehkan kita untuk memodifikasi hal yang sudah ada. Hal ini dikarenakan jika kita menambahkan sebuah fitur dan juga mengubah kode yang sudah ada, kode lain yang ada pada class tersebut berpotensi untuk rusak karena menjadi berbeda logicnya. Oleh karena itu, kita tidak boleh untuk memodifikasi yang sudah ada dan sudah benar, tetapi boleh jika ingin menambahkan fitur berdasarkan kode yang sudah ada.
    - Lebih mudah untuk dibaca
        - Pemanfaatan S.O.L.I.D dalam program berarti kita membagi-bagi class sesuai dengan fungsinya masing-masing, kemudian daripada menggunakan implementasi class untuk tipe data, kita menggunakan interface classnya. Practice dalam penggunaan S.O.L.I.D dapat membuat kode menjadi lebih terstruktur dan jelas apa yang dikerjakan pada suatu class sehingga bagi orang baru yang ingin mengerjakan program kita, dia dapat langsung tau secara intuisi apa fungsi dari class ini dll.
3. Beberapa kerugian dari tidak mengimplementasikan prinsip S.O.L.I.D dalam program yang dibuat adalah:
    - Kode akan sulit dimengerti oleh developer baru
        - Jika tidak menerapkan S.O.L.I.D principle, maka dalam satu class tidak akan ada batasan-batasan dan pembagian menjadi class-class yang lebih kecil. Hal ini akan menyulitkan bagi developer baru karena harus melihat program yang panjang dan tidak terstruktur dan perlu waktu yang cukup lama untuk memahami apa yang dikerjakan oleh class tersebut. Misalkan, terdapat sebuah class Product yang memiliki fungsi setter dan getter dan membeli product. Hal ini akan menyulitkan bagi developer karena bagaimana Product dapat dibeli di class Product itu sendiri. Padahal, maksud dari kodenya adalah dalam method membeli tersebut menerima parameter misalkan pembeli lalu di dalam method itu akan memanggil fungsi pembeli.beliProduct(). Oleh karena itu, diperlukan pemecahan class menjadi lebih kecil sesuai dengan apa yang dikerjakan oleh class tersebut.
    - Kode akan sulit untuk dimaintain
        - Jika kode dapat seenak-enaknya untuk diubah, maka fungsi lain akan kesulitan untuk dipastikan kebenaran kodenya karena misalkan terdapat fungsi yang membutuhkan return value dari pemanggilan fungsi lain tetapi fungsi lain ini diubah implementasinya sehingga return valuenya akan menjadi berbeda. Hal ini tentu akan menimbulkan logic error dari kode yang sudah ada. Oleh karena itu, perlu untuk mengimplementasikan S.O.L.I.D principle untuk menghindari hal ini.
    - Kode lebih sulit untuk diuji
        - Jika program memiliki dependensi yang tinggi, maka dalam pembuatan testing akan sulit untuk dilakukan karena mocking yang dilakukan akan banyak sekali sehingga akan merepotkan dalam pembuatan unit test. Lain halnya jika program sudah dipecah-pecah sesuai dengan fungsionalitasnya sehingga menurunkan tingkat dependensinya sehingga memudahkan untuk diuji.

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
