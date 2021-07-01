package checker;

import dataSupport.FileService;

public class FilesChecker {
    public boolean areFiles(){
        return FileService.isFile("IrishLottery/FullDrawsList")
                && FileService.isFile("IrishLottery/NumbersAfterMulti")
                && FileService.isFile("IrishLottery/ListOfNumbers")
                && FileService.isFile("EuroLottery/FullDrawsList")
                && FileService.isFile("EuroLottery/NumbersAfterMulti")
                && FileService.isFile("EuroLottery/ListOfNumbers")
                && FileService.isFile("PolishLottery/NumbersAfterMulti")
                && FileService.isFile("PolishLottery/ListOfNumbers")
                && FileService.isFile("PolishLottery/FullDrawsList")
                && FileService.isFile("Australian/FullDrawsList")
                && FileService.isFile("Australian/ListOfNumbers")
                && FileService.isFile("Australian/NumbersAfterMulti");
    }
}
