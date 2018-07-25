package lesson19.hw;

public class Controller {

    public void put(Storage storage, File file) throws Exception{
        if(storage != null && file != null){

            validate(storage, file);

            File[] storageFiles = storage.getFiles();
            if (storageFiles != null) {
                for(int i = 0; i < storageFiles.length; i++){
                    if (storageFiles[i] == null){
                        storageFiles[i] = file;
                        return;
                    }
                }
            }
        }
    }

    public void delete(Storage storage, File file) throws Exception {
        if(storage != null && file != null){
            File[] storageFiles = storage.getFiles();
            if (storageFiles != null && storageFiles.length > 0) {
                if (getFile(storage, file.getId()) != null){
                    for (int i = 0; i < storageFiles.length; i++){
                        if(storageFiles[i] != null && storageFiles[i].equals(file)){
                            storageFiles[i] = null;
                            storage.setStorageSize(storage.getStorageSize() - file.getSize());
                            return;
                        }
                    }
                }
            }
            throw new Exception("File " + file.getId() + " not found in a storage " + storage.getId());
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception{
        if (storageFrom != null && storageFrom.getFiles() != null && storageTo != null && storageTo.getFiles() != null){
            if (getFreeSpace(storageFrom) > getFreeSpace(storageTo)) {
                throw new Exception("There is not enough space in storage " + storageTo.getId());
            }

            File [] filesFrom = storageFrom.getFiles();
            for (File f : filesFrom){
                if(f != null){
                    transferFile(storageFrom, storageTo, f.getId());
                }
            }
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception{
        if (storageFrom != null && storageFrom.getFiles() != null && storageTo != null && storageTo.getFiles() != null) {
            File file = getFile(storageFrom, id);
            if(file == null){
                throw new Exception("File " + id + " not found in a storage " + storageFrom.getId());
            }
            //remove
            delete(storageFrom, file);
            //insert
            put(storageTo, file);
        }
    }

    /**
     * additional methods
     */
    private boolean checkFormat (Storage storage, File file){
        String [] formats = storage.getFormatSuported();
        if(formats != null && formats.length > 0) {
            for (String format : formats) {
                if(format.equals(file.getFormat())){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkFileName (String name){
        if (name != null && name.length() < 10){
            return true;
        }
        return false;
    }

    private File getFile (Storage storage, long id){
        File [] files = storage.getFiles();
        if(files != null && files.length>0) {
            for (File file : files) {
                if (file != null && file.getId() == id) {
                    return file;
                }
            }
        }
        return null;
    }
    //check null positions in array
    private boolean checkFreePlaceInStorage(Storage storage){
        for (File f : storage.getFiles()){
            if (f == null){
                return true;
            }
        }
        return false;
    }
    //check free space in storage....space in mb for example
    private long getFreeSpace (Storage storage){
        long freeSpace = 0;
        File [] files = storage.getFiles();
        if(files != null) {
            for (File f : storage.getFiles()) {
                if(f != null) {
                    freeSpace += f.getSize();
                }
            }
        }
        return freeSpace;
    }

    private void validate(Storage storage, File file) throws Exception{
        if(!checkFileName(file.getName())){
            throw new Exception("Wrong file name length. The length of file " + file.getId() + " is more than 10 ");
        }
        //check accepted or not a file format
        if (!checkFormat(storage, file)){
            throw new Exception("Format for file " + file.getId() + " is not supported in storage " + storage.getId());
        }
        //check file if it exist
        if (getFile(storage, file.getId()) != null){
            throw new Exception("File " + file.getId() + " is already exist in the storage");
        }
        //check free space on enough
        if(file.getSize() > getFreeSpace(storage)){
            throw new Exception("There is not enough space in storage " + storage.getId());
        }
        //check free place in storage
        if(!checkFreePlaceInStorage(storage)){
            throw new Exception("There is not a free place in storage " + storage.getId());
        }
    }
}
