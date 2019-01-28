package com.fys.learnjava.rocksdb;

import org.rocksdb.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RocksJavaTest {

    private static final String dbPath = "/Users/fys/workspace/rocksdb";

    static {
        RocksDB.loadLibrary();
    }

    RocksDB rocksDB;

    public void testDefaultCF() throws RocksDBException,IOException {
        Options options = new Options();
        options.setCreateIfMissing(true);

        if (!Files.isSymbolicLink(Paths.get(dbPath))) {
            Files.createDirectories(Paths.get(dbPath));
        }
        rocksDB = RocksDB.open(options, dbPath);

        byte[] key = "Hello".getBytes();
        byte[] value = "World".getBytes();

        rocksDB.put(key,value);

        byte[] getValue = rocksDB.get(key);
        System.out.println(new String(getValue));

        rocksDB.put("SecondKey".getBytes(), "SecondValue".getBytes());

        List<byte[]> keys = new ArrayList<>();
        keys.add(key);
        keys.add("SecondKey".getBytes());

        Map<byte[], byte[]> valueMap = rocksDB.multiGet(keys);
        for (Map.Entry<byte[], byte[]> entry : valueMap.entrySet()) {
            System.out.println(new String(entry.getKey()) + ":" + new String(entry.getValue()));
        }

        RocksIterator iter = rocksDB.newIterator();
        for (iter.seekToFirst(); iter.isValid(); iter.next()) {
            System.out.println("iter key:" + new String(iter.key()) + ", iter value:"+new String(iter.value()));
        }

        rocksDB.delete(key);
        System.out.println("After remove key:"+ new String(key));

        iter = rocksDB.newIterator();
        for (iter.seekToFirst(); iter.isValid(); iter.next()) {
            System.out.println("iter key:" + new String(iter.key()) + ", iter value:" + new String(iter.value()));
        }
    }

    public void testCertainCF() throws RocksDBException {
        String table = "CertainCF";
        String key = "CK";
        String value = "CV";

        List<ColumnFamilyDescriptor> cfd = new ArrayList<>();
        Options options = new Options();
        options.setCreateIfMissing(true);

        List<byte[]> cfs = RocksDB.listColumnFamilies(options, dbPath);
        if (cfs.size() > 0) {
            for(byte[] cf: cfs) {
                System.out.println("cf found:"+new String(cf));
                cfd.add(new ColumnFamilyDescriptor(cf, new ColumnFamilyOptions()));
            }
        } else {
            cfd.add(new ColumnFamilyDescriptor(RocksDB.DEFAULT_COLUMN_FAMILY,new ColumnFamilyOptions()));
        }

        List<ColumnFamilyHandle> columnFamilyHandles = new ArrayList<>();
        DBOptions dbOptions = new DBOptions();
        dbOptions.setCreateIfMissing(true);
        rocksDB = RocksDB.open(dbOptions, dbPath, cfd, columnFamilyHandles);
        for (int i = 0; i < cfd.size(); i++) {
            if (new String(cfd.get(i).columnFamilyName()).equals(table)) {
                rocksDB.dropColumnFamily(columnFamilyHandles.get(i));
            }
        }

        ColumnFamilyHandle columnFamilyHandle = rocksDB.createColumnFamily(new ColumnFamilyDescriptor(table.getBytes(), new ColumnFamilyOptions()));
        rocksDB.put(columnFamilyHandle, key.getBytes(), value.getBytes());
        byte[] getValue = rocksDB.get(columnFamilyHandle, key.getBytes());
        System.out.println("get Value : " + new String(getValue));

        rocksDB.put(columnFamilyHandle, "SecondKey".getBytes(), "SecondValue".getBytes());

        List<byte[]> keys = new ArrayList<byte[]>();
        keys.add(key.getBytes());
        keys.add("SecondKey".getBytes());

        List<ColumnFamilyHandle> handleList = new ArrayList<>();
        handleList.add(columnFamilyHandle);
        handleList.add(columnFamilyHandle);

        Map<byte[], byte[]> multiGet = rocksDB.multiGet(handleList, keys);
        for (Map.Entry<byte[], byte[]> entry : multiGet.entrySet()) {
            System.out.println(new String(entry.getKey()) + "--" + new String(entry.getValue()));
        }

        rocksDB.delete(columnFamilyHandle, key.getBytes());

        RocksIterator iter = rocksDB.newIterator(columnFamilyHandle);
        for (iter.seekToFirst(); iter.isValid(); iter.next()) {
            System.out.println(new String(iter.key()) + ":" + new String(iter.value()));
        }

    }


    public static void main(String[] args) throws Exception {
        RocksJavaTest rocksdb = new RocksJavaTest();
        //rocksdb.testDefaultCF();
        rocksdb.testCertainCF();
    }
}
