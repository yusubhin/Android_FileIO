package kr.co.hanbit.fileio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.co.hanbit.fileio.databinding.ActivityMainBinding
import java.io.*

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val directory_name = "memo"
        val filename = "memo01.txt"
        binding.btnSave.setOnClickListener {
            val content = binding.textWrite.text.toString() //textWrite의 내용을 가져와서
            writeTextFile(directory_name, filename, content)
        }

        val path = directory_name + "/" + filename
        val writtenContent = readTextFile(path) //읽은 내용이 있는지 검사
        binding.textRead.text = writtenContent
        /* 위의 코드와 같음(코드 간단히) */
        /* with(binding) {
            btnSave.setOnClickListener {
                val content = textWrite.text.toString() //textWrite의 내용을 가져와서
                val directory_name = "memo"
                val filename = "memo01.txt"
                writeTextFile(directory_name, filename, content)
            }
        }*/
    }

    /* 파일 읽기 메서드 */
    fun readTextFile(path: String): String {
        val fullpath = File(filesDir.path + "/" + path)
        if (!fullpath.exists()) return "" //파일이 없으면 공백 리턴

        val reader = FileReader(fullpath) //입력 stream인 Filereader로 file을 읽기
        val buffer = BufferedReader(reader) //BufferedReader에 담아 속도 향상
        var temp:String? = "" //buffer를 통해 한 줄씩 읽은 내용을 임시로 저장할 변수(temp는 null이 될 수 없으므로 :String?붙여줌)
        val result = StringBuffer() //모든 내용을 저장할 StringBuffer를 result 변수로 선언

        /* tmp를 사용하지 않을 경우, null이 나오면 그 앞의 내용을 담을 수가 없음
        while (true) {
            if (buffer.readline() == null) break
            result.append(buffer)
        } */
        while (true) {
            temp = buffer.readLine()
            if (temp == null) break
            else result.append(temp).append("\n") //temp가 line별로 읽어오므로 줄바꿈문자 인식불가능-> append("\n")추가
        }
        buffer.close()
        reader.close()
        return result.toString()
    }

    /* 파일 쓰기 메서드 */
    fun writeTextFile(directory:String, filename:String, content:String) {
        /* 앱 기본경로 / files / memo 에 파일 생성 */
        val dir = File(filesDir.path + "/" + directory) //파일 읽기
        //filesDir.path: 앱 별로 가지고 있는 기본 저장소
        if (!dir.exists()) {
            dir.mkdirs() //디렉토리 생성
        }

        val fullpath = dir.path + "/" + filename //directory + "/" + filename와 같음
        val writer = FileWriter(fullpath) //FileWriter 생성
        val buffer = BufferedWriter(writer) //속도 향상
        buffer.write(content) //buffer로 내용 쓰기
        buffer.close()
        writer.close()
    }
    /* 저장되는 파일 찾기: View->Tool Windows->Device File Explorer에서
        data/data/기본패키지명(line 1) 안에 filesDir(line 36)로 생성된 files폴더 확인가능
        안에 memo 폴더와 그안에 memo01.kt 확인가능
        파일 추가하고싶다면 directory_name을 memo02.txt로 바꾼 후 재실행 -> memo폴더에서 우클릭 -> 동기화클릭
        내부 저장소 이므로 권한 필요x
     */
}