package lesson19.hw;

public class Controller {

    public File put(Storage storage, File file) throws Exception{
        if(file != null) {

            validateFile(storage, file);
            insertFile(storage.getFiles(), file);

            return file;
        }
        return null;
    }

    public void delete(Storage storage, File file) throws Exception {
        if (storage.getFiles() != null && file != null) {

            getFile(storage, file.getId());

            for (int i = 0; i < storage.getFiles().length; i++){
                if(storage.getFiles()[i] != null && storage.getFiles()[i].equals(file)){
                    storage.getFiles()[i] = null;
                    return;
                }
            }
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo)throws Exception{
        //if (storageFrom.getFiles() != null && storageTo.getFiles() != null){

            File [] filesFrom = storageFrom.getFiles();

            validateTranferFiles(storageFrom, storageTo, filesFrom);

            for (File f : filesFrom){
                if(f != null){
                    transferFile(storageFrom,storageTo, f.getId());
                }
            }
        //}
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception{
        //if (storageFrom.getFiles() != null && storageTo.getFiles() != null) {

            File file = getFile(storageFrom, id);
            if(file != null) {
                //insert
                insertFile(storageTo.getFiles(), file);
                //remove
                delete(storageFrom, file);
            }
            throw new Exception("File " + id + " not found in storage " + storageFrom.getId());
        //}
    }

    /**
     * additional methods
     */

    private void validateFile(Storage storage, File file) throws Exception{

        //check accepted or not a file format in storage
        checkFormat(storage, file);

        //check file if it exist
        checkFileInStorage(storage, file.getId());

        //check free space in the storage
        checkFreeSpace(storage, file);

        //check free place in storage
        checkFreePlaceInStorage(storage);
    }

    private void checkFormat (Storage storage, File file) throws Exception{
        String [] formats = storage.getFormatSuported();
        if(formats != null && formats.length > 0) {
            for (String format : formats) {
                if(format.equals(file.getFormat())){
                    return;
                }
            }
        }
        throw new Exception("Format for file " + file.getId() + " is not supported in storage " + storage.getId());
    }

    private void checkFileInStorage(Storage storage, long id) throws Exception{
        if(getFile(storage, id) != null){
            throw new Exception("File " + id + " already exist in a storage " + storage.getId());
        }
    }

    //return null of file not found
    private File getFile (Storage storage, long id)throws  Exception{
        if(storage.getFiles() != null && storage.getFiles().length>0) {
            for (File file : storage.getFiles()) {
                if (file != null && file.getId() == id) {
                    return file;
                }
            }
        }
        return null;
    }

    private void checkFreeSpace(Storage storageTo, File file) throws Exception{
        if (storageTo.getFiles() == null || getFreeSpace(storageTo) <= file.getSize()) {
            throw new Exception("There is not enough free space in storage " + storageTo.getId());
        }
    }

    //check free space in storage....space in mb for example
    private long getFreeSpace (Storage storage){
        long filesSize = 0;
            for (File f : storage.getFiles()) {
                if(f != null) {
                    filesSize += f.getSize();
                }
            }
        return storage.getStorageSize() - filesSize;
    }

    //check null positions in array
    private void checkFreePlaceInStorage(Storage storage)throws Exception{
        for (File f : storage.getFiles()){
            if (f == null){
                return;
            }
        }
        throw new Exception("There is not a free place in storage " + storage.getId());
    }

    private void validateTranferFiles(Storage storageFrom, Storage storageTo, File [] filesFrom) throws Exception {
        //check if enough space for files from storageFrom in storageTo
        if(getFreeSpace(storageTo) < (storageFrom.getStorageSize() - getFreeSpace(storageFrom))){
            throw new Exception("There isn't enough free space for files from storage " + storageFrom.getId() + " in storage " + storageTo.getId());
        }
        for (File f : filesFrom){
            if(f != null){
                validateFile(storageTo, f);
            }
        }
    }

    private void insertFile(File[] files, File file){
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i] == null) {
                    files[i] = file;
                }
            }
        }
    }
}