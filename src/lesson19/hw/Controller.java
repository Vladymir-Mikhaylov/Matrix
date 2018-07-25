package lesson19.hw;

public class Controller {

    public void put(Storage storage, File file) throws Exception{
        validateFile(storage, file);

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

    public void delete(Storage storage, File file) throws Exception {
        File[] storageFiles = storage.getFiles();
        if (storageFiles != null) {

            checkFileInStorage(storage, file.getId());

            for (int i = 0; i < storageFiles.length; i++){
                if(storageFiles[i] != null && storageFiles[i].equals(file)){
                    storageFiles[i] = null;
                    return;
                }
            }
        }
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception{
        if (storageFrom.getFiles() != null && storageTo.getFiles() != null){

            File [] filesFrom = storageFrom.getFiles();

            for (File f : filesFrom){
                if(f != null){
                    transferFile(storageFrom, storageTo, f.getId());
                }
            }
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception{
        if (storageFrom.getFiles() != null && storageTo.getFiles() != null) {

            File file = getFile(storageFrom, id);

            //remove
            delete(storageFrom, file);
            //insert
            put(storageTo, file);
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

    private boolean checkFormat (Storage storage, File file) throws Exception{
        String [] formats = storage.getFormatSuported();
        if(formats != null && formats.length > 0) {
            for (String format : formats) {
                if(format.equals(file.getFormat())){
                    return true;
                }
            }
        }
        throw new Exception("Format for file " + file.getId() + " is not supported in storage " + storage.getId());
    }

    private void checkFileInStorage(Storage storage, long id) throws Exception{
        if(getFile(storage, id) == null){
            throw new Exception("File " + id + " not found in a storage " + storage.getId());
        }
    }

    private File getFile (Storage storage, long id)throws  Exception{
        File [] files = storage.getFiles();
        if(files != null && files.length>0) {
            for (File file : files) {
                if (file != null && file.getId() == id) {
                    return file;
                }
            }
        }
        throw new Exception("File " + id + " not found in a storage");
    }

    private void checkFreeSpace(Storage storageTo, File file) throws Exception{
        if (getFreeSpace(storageTo) < file.getSize()) {
            throw new Exception("There is not enough free space in storage " + storageTo.getId());
        }
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

    //check null positions in array
    private boolean checkFreePlaceInStorage(Storage storage)throws Exception{
        for (File f : storage.getFiles()){
            if (f == null){
                return true;
            }
        }
        throw new Exception("There is not a free place in storage " + storage.getId());
    }
}
