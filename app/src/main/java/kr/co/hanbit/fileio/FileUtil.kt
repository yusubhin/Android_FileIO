package kr.co.hanbit.fileio

import java.io.*
/*
open class FileUtil {
    /* 파일 읽기 메서드 */
    open fun readTextFile(fullPath: String): String {
        val file = File(fullPath)
        if (!file.exists()) return "" //파일이 없으면 공백 리턴

        val reader = FileReader(file) //입력 stream인 Filereader로 file을 읽기
        val buffer = BufferedReader(reader) //BufferedReader에 담아 속도 향상
        var temp = "" //buffer를 통해 한 줄씩 읽은 내용을 임시로 저장할 변수
        val result = StringBuffer() //모든 내용을 저장할 StringBuffer를 result 변수로 선언
        while (true) {
            temp = buffer.readLine()
            if (temp == null) break;
            else result.append(buffer)
        }
        buffer.close()
        return result.toString()
    }

    /* 파일 쓰기 메서드 */
    open fun writeTextFile(directory: String, filename: String, content: String) {
        /* 파라미터 1: 디렉터리, 2: 파일명, 3: 작성할 내용 */
        val dir = File(filesDir.path + "/" + directory) //파일 읽기
        if (!dir.exists()) {
            dir.mkdirs() //디렉토리 생성
        }
        val writer = FileWriter(directory + "/" + filename) //FileWriter 생성
        val buffer = BufferedWriter(writer) //속도 향상
        buffer.write(content) //buffer로 내용 쓰기
        buffer.close()
    }
}
*/