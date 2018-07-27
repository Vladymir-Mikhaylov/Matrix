package lesson19.hw;

public class Controller {

    public File put(Storage storage, File file) throws Exception{
        if(file != null) {

            validateFile(storage, file);

            if (storage.getFiles() != null) {
                for (int i = 0; i < storage.getFiles().length; i++) {
                    if (storage.getFiles()[i] == null) {
                        storage.getFiles()[i] = file;
                        return file;
                    }
                }
            }
        }
        return null;
    }

    public void delete(Storage storage, File file) throws Exception {
        File[] storageFiles = storage.getFiles();
        if (storageFiles != null && file != null) {

            getFile(storage, file.getId());

            for (int i = 0; i < storageFiles.length; i++){
                if(storageFiles[i] != null && storageFiles[i].equals(file)){
                    storageFiles[i] = null;
                    return;
                }
            }
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo){
        if (storageFrom.getFiles() != null && storageTo.getFiles() != null){

            File [] filesFrom = storageFrom.getFiles();

            for (File f : filesFrom){
                if(f != null){
                    try {
                        transferFile(storageFrom, storageTo, f.getId());
                    }catch (Exception e){
                        e.getMessage();
                    }
                }
            }
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception{
        if (storageFrom.getFiles() != null && storageTo.getFiles() != null) {

            File file = getFile(storageFrom, id);
            if(file != null) {
                //insert
                put(storageTo, file);
                //remove
                delete(storageFrom, file);
            }
            throw new Exception("File " + id + "not found in storage " + storageFrom.getId());
        }
    }



    /**
     * additional methods
     */

    private void validateFile(Storage storage, File file) throws Exception{

        //check fileName length
        checkFileName(file.getName());

        //check accepted or not a file format in storage
        checkFormat(storage, file);

        //check file if it exist
        checkFileInStorage(storage, file.getId());

        //check free space in the storage
        checkFreeSpace(storage, file);

        //check free place in storage
        checkFreePlaceInStorage(storage);
    }

    private void checkFileName (String name)throws Exception{
        if (name == null && name.length() > 10){
            throw new Exception("Wrong file name length. The length of file ");
        }
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
        if (file == null || storageTo.getFiles() == null || getFreeSpace(storageTo) <= file.getSize()) {
            throw new Exception("There is not enough free space in storage " + storageTo.getId());
        }
    }

    //check free space in storage....space in mb for example
    private long getFreeSpace (Storage storage){
        long filesSize = 0;
        if(storage.getFiles() != null) {
            for (File f : storage.getFiles()) {
                if(f != null) {
                    filesSize += f.getSize();
                }
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
}
