import java.util.LinkedList;
import java.util.Iterator;

import Includes.DictionaryEntry;
import Includes.HashTableEntry;
import Includes.KeyAlreadyExistException;
import Includes.KeyNotFoundException;
import Includes.NullKeyException;

import java.lang.reflect.Array;

public class COL106Dictionary<K, V> {

    private LinkedList<DictionaryEntry<K, V>> dict;
    /*
     * dict is a Linked-List, where every node of linked-list is of type DictionaryEntry.
     * DictionaryEntry is a key-value pair, where the type of key and value is K and V respectively.
     */ 
    public LinkedList<HashTableEntry<K, V>>[] hashTable;
    /*
     * hashTable is an array of Linked-Lists which is initialized by the COL106Dictionary constructor.
     * Each index of hashTable stores a linked-list whose nodes are of type HashTableEntry
     * HashTableEntry is a key-address pair, where the type of key is K and the corresponding address is the address of the DictionaryEntry in the linked-list corresponding to the key of HashTableEntry
     */ 
    
    private int hsize;
    private int size=0;

    @SuppressWarnings("unchecked")
    COL106Dictionary(int hashTableSize) {
        dict = new LinkedList<DictionaryEntry<K, V>>();
        // This statement initiailizes a linked-list where each node is of type DictionaryEntry with key and value of type K and V respectively.
        hashTable = (LinkedList<HashTableEntry<K, V>>[]) Array.newInstance(LinkedList.class, hashTableSize);
        // This statement initiailizes the hashTable with an array of size hashTableSize where at each index the element is an instance of LinkedList class and
        // this array is type-casted to an array of LinkedList where the LinkedList contains nodes of type HashTableEntry with key of type K. 
        hsize=hashTableSize;
    }

    public void insert(K key, V value) throws KeyAlreadyExistException, NullKeyException {
        /*
         * To be filled in by the student
         * Input: A key of type K and it corresponding value of type V
         * Working: Inserts the argumented key-value pair in the Dictionary in O(1)
         */
        if (key==null){
            throw new NullKeyException();
        }
        else{
            if(hashTable[hash(key)]==null){
                int hash_key_value = hash(key);
                LinkedList<HashTableEntry<K,V>> newLL= new LinkedList<HashTableEntry<K,V>>() ;
                hashTable[hash_key_value]=newLL;
                DictionaryEntry<K,V>  obj = new DictionaryEntry<K,V>(key, value);
                this.dict.add(obj);
                HashTableEntry<K,V> obj_HashTableEntry= new HashTableEntry<K,V>(key, obj);
                newLL.add(obj_HashTableEntry);
                size++;
            }
            else{
                Iterator<HashTableEntry<K,V>> it = hashTable[hash(key)].iterator();
                while(it.hasNext()) {
                K i = it.next().key;
                    if(i.toString().equals(key.toString())) {
                        throw new KeyAlreadyExistException();
                    }
                }
                int hash_key_value = hash(key);
               
                DictionaryEntry<K,V>  obj = new DictionaryEntry<K,V>(key, value);
                this.dict.add(obj);
                HashTableEntry<K,V> obj_HashTableEntry= new HashTableEntry<K,V>(key, obj);
                this.hashTable[hash_key_value].add(obj_HashTableEntry);
                size++;
            }
        }
    }

    public V delete(K key) throws NullKeyException, KeyNotFoundException{
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the associated value of type V with the argumented key
         * Working: Deletes the key-value pair from the Dictionary in O(1)
         */

        // exception handling
        if (key==null){
            throw new NullKeyException();
        }        
        int j=hash(key);
        V value_of_key=null;
        if(hashTable[j]==null){
            throw new KeyNotFoundException();
        }
        else{

            Iterator<HashTableEntry<K,V>> hashtable_LL_iterate = hashTable[j].iterator();
            int k=0;
            while(hashtable_LL_iterate.hasNext()) {
                k++;
                HashTableEntry<K,V> i = hashtable_LL_iterate.next();
                if((i.key.toString()).equals(key.toString())) {
                    value_of_key=i.dictEntry.value;
                    dict.remove(i.dictEntry);    // remove
                    hashTable[j].remove(i);
                    size--;
                    break;
                }
                else if (!(i.key.toString().equals(key.toString())) && k==hashTable[j].size()){
                    throw new KeyNotFoundException();
                }
        }
 
    }
        return value_of_key;
    }

    public V update(K key, V value) throws NullKeyException, KeyNotFoundException{
        // System.out.println("update");
       
        if (key==null){
            throw new NullKeyException();
        }
        int j=hash(key);
        V value_of_key=null;
        if(hashTable[j]!=null){
           
            Iterator<HashTableEntry<K,V>> hashtable_LL_iterate = hashTable[j].iterator();
            int k=0;
            while(hashtable_LL_iterate.hasNext()) {
                k++;
                HashTableEntry<K,V> i = hashtable_LL_iterate.next();
                
                    if(i.key.toString().equals(key.toString())) {
                        value_of_key=i.dictEntry.value;
                        // hashTable[j].remove(i);
                        i.dictEntry.value=value;
                        break;
                    }
                    else if (!(i.key.toString().equals(key.toString())) && k==hashTable[j].size()){
                        throw new KeyNotFoundException();
                    }
        }
    }
        else{
            throw new KeyNotFoundException();
            }
    
        return value_of_key;
    }

    public V get(K key) throws NullKeyException, KeyNotFoundException {
        // System.out.println("get");
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the associated value of type V with the argumented key in O(1)
         */
        if (key==null){
            throw new NullKeyException();
        }
        int j=hash(key);
        V value_of_key=null;
        if(hashTable[j]==null){
            throw new KeyNotFoundException();
        }
        else{
            
            Iterator<HashTableEntry<K,V>> hashtable_LL_iterate = hashTable[j].iterator();
            int k=0;
            while(hashtable_LL_iterate.hasNext()) {
                k++;
                HashTableEntry<K,V> i = hashtable_LL_iterate.next();
                if(i.key.toString().equals(key.toString())) {
                    value_of_key=i.dictEntry.value;
                    // hashTable[j].remove(i);
                    break;
                }
                else if (!(i.key.toString().equals(key.toString())) && k==hashTable[j].size()){
                    throw new KeyNotFoundException();
                }
            }
        }

        
        return value_of_key;

    }

    public int size() {
        // System.out.println("size");
        /*
         * To be filled in by the student
         * Return: Returns the size of the Dictionary in O(1)
         */
        return size;
    }

    @SuppressWarnings("unchecked")
    public K[] keys(Class<K> cls) {
        // System.out.println("keys");
        /*
         * To be filled in by the student
         * Return: Returns array of keys stored in dictionary.
         */

        K[] key_Array = (K[]) Array.newInstance(cls, size);
        Iterator<DictionaryEntry<K,V>> dictionary_entry_obj = dict.iterator();
        int i=0;
        while(dictionary_entry_obj.hasNext()){
            K key_entry= dictionary_entry_obj.next().key;
            key_Array[i]=key_entry;
            i++;
        }
        
        return key_Array;
    }

    @SuppressWarnings("unchecked")
    public V[] values(Class<V> cls) {
        // System.out.println("values");
        /*
         * To be filled in by the student
         * Return: Returns array of keys stored in dictionary.
         */

        V[] Value_Array = (V[]) Array.newInstance(cls, size);
        Iterator<DictionaryEntry<K,V>> dictionary_entry_obj = dict.iterator();
        int i=0;
        while(dictionary_entry_obj.hasNext()){
            V Value_entry= dictionary_entry_obj.next().value;
            Value_Array[i]=Value_entry;
            i++;
        }
        return Value_Array;
    }

    public int hash(K key) {
        /*
         * To be filled in by the student
         * Input: A key of type K
         * Return: Returns the hash of the argumented key using the Polynomial Rolling
         * Hash Function.
         */
        String a = key.toString();
        int hashv=0;
        for (int i=0 ; i<a.length();i++ ){
            hashv=((131*hashv)+(a.charAt(a.length()-1-i) +1))%hsize;

        }
        return hashv;
    }
    
}
