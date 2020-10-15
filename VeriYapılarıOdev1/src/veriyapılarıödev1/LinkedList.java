/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veriyapılarıödev1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @File VeriYapılarıÖdev1
 * @description:
 * tekli bir liste oluşturuyoruz bu listemiz bütün düğümlerin yanı sıra ana düğümleride tutuyor. Ana düğümlerimizinde liste düğüm özelliği var liste düğümleri ana düğümlerin
 * bağlı olduğu düğümlerin bilgisini ve bu düğümlerin ana düğüme kaç kere bağlı olduğu bilgisini tutuyor. Ayrıca en çok ardışık olan karakterleri ,en az olan karakterleri ve 
 * bize sorulan karakterin ardından ne geldiği gibi bazı bilgileri bize bastıran metotlarımız var.
 * @assignment 1. Ödev
 * @date 22.04.2020
 * @author Enes Durmuş  --   enes.durmus@stu.fsm.edu.tr
 */
public class LinkedList<T> {

    private Node<T> head;
    File file;
    private mainNode<T> anaDugum;

    public LinkedList() {
    }

    public LinkedList(File file) throws IOException {
        readFile(file);
    }

// Yolu verilen dosyanın bütün karakterlerini tek tek eğer harf ise listemize ekliyoruz.
    void readFile(File file) throws FileNotFoundException, IOException {
        FileReader inputStream = new FileReader(file);
        int character;
        while ((character = inputStream.read()) != -1) {
            if ((character >= 97 && character <= 122) || (character >= 65 && character <= 90)) { // burada karakterin harf olup olmadığını buluyoruz büyük harfede duyarlı,
                if (Character.isUpperCase(character)) { // bu metot bize karakter büyük harf ise true döndürüyor.
                    addLast((T) Character.valueOf((char) Character.toLowerCase(character)));  // addLast metodu ile listemizin sonuna o anki karakterimizi ekliyoruz.
                    addMainNode((T) Character.valueOf((char) Character.toLowerCase(character))); //  bu metod ile mainNode listemizide oluşturuyoruz.
                } else {
                    addLast((T) Character.valueOf((char) character));
                    addMainNode((T) Character.valueOf((char) character));
                }
            } else {
                addSpecialCharacterInfToMainNode(); // eğer karakter bir harf değilse düğümümüzün isNextNodeSpecial çzelliğini true 'ya çeviriyor bu metod
            }

        }
        addListNode();

    }

    // readFile içerisinde bu metodumuzu çağırıyoruz.
    private void addMainNode(T newData) {
        // readFile dan aldığımız karakteri burada gerekli kontrolleri yaparak mainNode listesine ekliyoruz.

        mainNode<T> newNode = new mainNode(newData);

        if (anaDugum == null) {
            anaDugum = newNode; // eğer listemizin ana düğümü boşsa direk burada ekliyoruz ana düğümümüzün ilk elemanını.
        } else {
            boolean isNodeExist = false;  // Burada bütün ana düğüm listemizdeki elemanları yeni ana düğümün datasıyla karşılaştırıyoruz eğer varsa isNodeExist i true ya çeviriyruz
            mainNode<T> temp = anaDugum;
            while (temp != null) {
                if (temp.data.equals(newNode.data)) {
                    isNodeExist = true;
                }
                temp = temp.nextNode;
            }
            if (!isNodeExist) {  //eğer yeni düğümümüz ana düğümde yoksa ana düğümümüze ekliyoruz.
                mainNode<T> temp2 = anaDugum;
                while (temp2.nextNode != null) {
                    temp2 = temp2.nextNode;
                }
                temp2.nextNode = newNode;
            }
        }
    }

    private void addSpecialCharacterInfToMainNode() {
        Node<T> temp = head;

        while (temp.nextNode != null) {
            temp = temp.nextNode;
        }
        temp.isNextNodeSpecial = true;

    }

    private void addListNode() {
        mainNode<T> tempMainNodes = anaDugum;
        while (tempMainNodes != null) { // Ana düğümlerimiz içinde 
            Node<T> tempHead = head;
            while (tempHead.nextNode != null) {  // bütün düğümlerimizi her ana düğüm için dönüyoruz.
                if (!tempHead.isNextNodeSpecial) { // eğer karakterimiz boşluk nokta vesaire değilse içeri giriyor.
                    if (tempMainNodes.data == tempHead.data) {
                        if (tempMainNodes.listNode == null) {  //eğer ana düğümümüzün liste düğümü boş ise burada liste düğümünü ekliyeceğiz.
                            listNode<T> newListNode = new listNode<T>(tempHead.nextNode.data); // yeni bir liste düğümü oluşturuyoruz bu düğümün datası o an bulunduğumuz düğümün ,
                            newListNode.sayac += 1;                                            // bir sonraki düğümü oluyor. yani bizim bağlamamız gereken karakter.
                            tempMainNodes.listNode = newListNode;
                        } else {
                            boolean isListNodeExist = false;                                   // eğer liste düğümümüz doluysa buraya giriyor.
                            listNode<T> tempListNode1 = tempMainNodes.listNode;                // burada ana düğümümüzün bütün liste düğümlerini kontrol ediyor.
                            while (tempListNode1 != null) {                                    // eğer bir eşitlik varsa sayacı artırıyor ve isLİstNodeExist değişkenini,
                                if (tempListNode1.data.equals(tempHead.nextNode.data)) {       // true yapıyor.
                                    isListNodeExist = true;
                                    tempListNode1.sayac += 1;
                                }
                                tempListNode1 = tempListNode1.nextNode;
                            }
                            if (!isListNodeExist) {                                             // burada isListNodeExist değişkenine bakıyoruz eğer list node true olduysa yeni bir
                                listNode<T> tempListNode2 = tempMainNodes.listNode;             // liste düğümü eklemeyecek ancak değişkenimiz hala false ise içeri girip
                                listNode<T> newListNode = new listNode<T>(tempHead.nextNode.data); // son liste düğümünün sonraki düğümüne ekleme yapıcak.
                                while (tempListNode2.nextNode != null) {
                                    tempListNode2 = tempListNode2.nextNode;
                                }
                                newListNode.sayac += 1;
                                tempListNode2.nextNode = newListNode;
                            }
                        }
                    }
                }
                tempHead = tempHead.nextNode;
            }
            tempMainNodes = tempMainNodes.nextNode;
        }

    }

    void ardisikKarakterler(char k) {
        mainNode tempMainNode = anaDugum;
        boolean isCharacterExist = false;               // Bütün ana düğümleri dönüyoruz eğer ana düğümümüzün datası bize gönderilen karaktere eşit ise o ana düğümün liste
        while (tempMainNode.nextNode != null) {         // düğümlerini dönüyoruz ve ekrana bastırıyoruz.
            if (tempMainNode.data.equals(k)) {
                isCharacterExist = true;
                listNode tempListNode = tempMainNode.listNode;
                while (tempListNode != null) {
                    System.out.println((k + " karakterinden sonra: " + tempListNode.data + " geliyor..."));
                    tempListNode = tempListNode.nextNode;
                }

            }
            tempMainNode = tempMainNode.nextNode;
        }
        if(!isCharacterExist){
            System.out.println("Böyle bir karakter listede bulunmamaktadır..");
        }
    }

    void enCokArdisik() {
        mainNode tempMainNode = anaDugum;                                       // İlk olarak enBuyukLısteDugumu ilk ana düğümün ilk liste düğümünü ekliyoruz ve
        listNode enBuyukLısteDugumu = anaDugum.listNode;                        // hangiAnaDugumeAıt değişkeninede ana düğümümüzü atıyoruz .
        mainNode hangiAnaDugumeAıt = anaDugum;                                  // Her ana düğümün liste düğümlerini dönüyoruz ve her bir liste düğüm için o anki liste düğümünün
        while (tempMainNode != null) {                                          // sayacı ile dışarda atadığımız liste düğümünün sayaçlarını karşılaştırıp dışardaki değişkenimize 
            listNode tempListNode = tempMainNode.listNode;                      // atıyoruz.
            while (tempListNode != null) {
                enBuyukLısteDugumu = tempListNode.sayac >= enBuyukLısteDugumu.sayac ? tempListNode : enBuyukLısteDugumu;
                hangiAnaDugumeAıt = tempListNode.sayac >= enBuyukLısteDugumu.sayac ? tempMainNode : hangiAnaDugumeAıt;
                tempListNode = tempListNode.nextNode;
            }

            tempMainNode = tempMainNode.nextNode;
        }
        System.out.println(hangiAnaDugumeAıt.data + " ve " + enBuyukLısteDugumu.data + " karakterleri " + enBuyukLısteDugumu.sayac + " kere peş peşe gelerek en çok ardışık olmuşlardır...");
    }

    void enCokArdisik(char k) {
        if ((k >= 97 && k <= 122) || (k >= 65 && k <= 90)) {                    // Verilen karakterin harf olup olmadığını kontrol ediyoruz.
            mainNode tempMainNode = anaDugum;                                   // ana düğümleri dönüyoruz eğer bize gönderilen karaktere eşit ise o ana düğümün
            listNode enKucukLısteDugumu = anaDugum.listNode;                    // liste düğümlerinin sayaçlarını kontrol edip bize gönderilen karakterin en çok ardışığını buluyoruz.
            mainNode hangiAnaDugumeAıt = anaDugum;
            boolean isCharacterExist = false;
            boolean isListNodeExist = false;
            while (tempMainNode != null) {
                if (tempMainNode.data.equals(k)) {
                    isCharacterExist = true;
                    enKucukLısteDugumu = tempMainNode.listNode;
                    hangiAnaDugumeAıt = tempMainNode;
                    listNode tempListNode = tempMainNode.listNode;
                    while (tempListNode != null) {
                        enKucukLısteDugumu = tempListNode.sayac >= enKucukLısteDugumu.sayac ? tempListNode : enKucukLısteDugumu;
                        hangiAnaDugumeAıt = tempListNode.sayac >= enKucukLısteDugumu.sayac ? tempMainNode : hangiAnaDugumeAıt;
                        tempListNode = tempListNode.nextNode;
                    }
                }

                tempMainNode = tempMainNode.nextNode;
            }
            if (isCharacterExist) {
                if (hangiAnaDugumeAıt.listNode == null) {
                    System.out.println("Bu ana düğümün liste düğümü bulunmamaktadır.");
                } else {
                    System.out.println(hangiAnaDugumeAıt.data + " ve " + enKucukLısteDugumu.data + " karakterleri " + enKucukLısteDugumu.sayac + " kere peş peşe gelerek en çok ardışık olmuşlardır...");
                }

            } else {
                System.out.println("Böyle bir karakter listede bulunmamaktadır..");
            }
        } else {
            System.out.println("Geçerli bir karakter giriniz...");
        }
    }

    void enAzArdisik() {
        mainNode tempMainNode = anaDugum;                                       // en çok ardışık metodu ile aynı mantıkda çalışıyor farklı olarak sayaçların küçük olup olmadığını
        listNode enKucukLısteDugumu = anaDugum.listNode;                        // kontrol ediyor.
        mainNode hangiAnaDugumeAıt = anaDugum;
        while (tempMainNode != null) {
            listNode tempListNode = tempMainNode.listNode;
            while (tempListNode != null) {
                enKucukLısteDugumu = tempListNode.sayac <= enKucukLısteDugumu.sayac ? tempListNode : enKucukLısteDugumu;
                hangiAnaDugumeAıt = tempListNode.sayac <= enKucukLısteDugumu.sayac ? tempMainNode : hangiAnaDugumeAıt;
                tempListNode = tempListNode.nextNode;
            }

            tempMainNode = tempMainNode.nextNode;
        }
        System.out.println(hangiAnaDugumeAıt.data + " ve " + enKucukLısteDugumu.data + " karakterleri " + enKucukLısteDugumu.sayac + " kere peş peşe gelerek en az ardışık olmuşlardır...");
    }

    void enAzArdisik(char k) {
        if ((k >= 97 && k <= 122) || (k >= 65 && k <= 90)) {
            mainNode tempMainNode = anaDugum;
            listNode enKucukLısteDugumu = anaDugum.listNode;
            mainNode hangiAnaDugumeAıt = anaDugum;
            boolean isCharacterExist = false;
            boolean isListNodeExist = false;
            while (tempMainNode != null) {
                if (tempMainNode.data.equals(k)) {
                    isCharacterExist = true;
                    enKucukLısteDugumu = tempMainNode.listNode;
                    hangiAnaDugumeAıt = tempMainNode;
                    listNode tempListNode = tempMainNode.listNode;
                    while (tempListNode != null) {
                        enKucukLısteDugumu = tempListNode.sayac <= enKucukLısteDugumu.sayac ? tempListNode : enKucukLısteDugumu;
                        hangiAnaDugumeAıt = tempListNode.sayac <= enKucukLısteDugumu.sayac ? tempMainNode : hangiAnaDugumeAıt;
                        tempListNode = tempListNode.nextNode;
                    }
                }

                tempMainNode = tempMainNode.nextNode;
            }
            if (isCharacterExist) {
                if (hangiAnaDugumeAıt.listNode == null) {
                    System.out.println("Bu ana düğümün liste düğümü bulunmamaktadır.");
                } else {
                    System.out.println(hangiAnaDugumeAıt.data + " ve " + enKucukLısteDugumu.data + " karakterleri " + enKucukLısteDugumu.sayac + " kere peş peşe gelerek en az ardışık olmuşlardır...");
                }

            } else {
                System.out.println("Böyle bir karakter listede bulunmamaktadır..");
            }
        } else {
            System.out.println("Geçerli bir karakter giriniz...");
        }
    }

    void printListNodes() {
        mainNode<T> tempMainNodes = anaDugum;
        System.out.println(">>>Liste Dugumlerimiz<<<");
        while (tempMainNodes != null) {
            if (tempMainNodes.listNode == null) {
                System.out.println(tempMainNodes.data + "  düğümünün liste düğümü bulunmamaktadır.");
            } else {
                listNode<T> tempListNode = tempMainNodes.listNode;
                while (tempListNode != null) {
                    System.out.print(tempMainNodes.data + " " + String.valueOf(tempListNode.data) + " " + String.valueOf(tempListNode.sayac) + " ");
                    tempListNode = tempListNode.nextNode;
                }
                System.out.println("");
            }
            tempMainNodes = tempMainNodes.nextNode;
        }

    }

    void printMainNodes() {
        mainNode<T> temp = anaDugum;
        System.out.println(">>>Ana Dugumlerimiz<<<");
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.nextNode;
        }

        System.out.println("null");
    }

    // Buradan aşağısı labda yazmış olduğumuz kodlar.
    void addFirst(Node<T> newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            newNode.nextNode = head;
            head = newNode;
        }
    }

    void addFirst(T newData) {
        addFirst(new Node<>(newData));
    }

    void addLast(Node<T> newNode) {
        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> temp = head;

            while (temp.nextNode != null) {
                temp = temp.nextNode;
            }

            temp.nextNode = newNode;
        }
    }

    void addLast(T newData) {
        addLast(new Node<>(newData));
    }

    boolean addAfter(T search, T newData) {
        if (isEmpty()) {
            System.out.println("Empty list !");
        } else {
            Node<T> temp = head;

            while (temp != null && !temp.data.equals(search)) {
                temp = temp.nextNode;
            }

            if (temp != null) {
                Node<T> newNode = new Node<>(newData);
                newNode.nextNode = temp.nextNode;
                temp.nextNode = newNode;
                return true;
            }
        }
        return false;
    }

    Node<T> remove(T data) {
        Node<T> removedNode = null;

        if (isEmpty()) {
            System.out.println("Empty list !");
        } else {

            if (head.data.equals(data)) {
                removedNode = head;
                head = head.nextNode;
            } else {
                Node<T> temp = head;

                while (temp.nextNode != null && !temp.nextNode.data.equals(data)) {
                    temp = temp.nextNode;
                }

                if (temp.nextNode != null) {
                    removedNode = temp.nextNode;
                    temp.nextNode = temp.nextNode.nextNode;
                }
            }
        }

        return removedNode;
    }

    void print() {
        Node<T> temp = head;

        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.nextNode;
        }

        System.out.println("null");
    }

    boolean isEmpty() {
        return head == null;
    }

    int size() {
        Node<T> temp = head;
        int count = 0;

        while (temp != null) {
            count++;
            temp = temp.nextNode;
        }

        return count;
    }

}
