package br.com.mydiagram.service

import mu.KotlinLogging
import org.springframework.core.io.InputStreamResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.*
import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributes
import java.util.*


@Service
class FileService {

    private val logger = KotlinLogging.logger {  }

    fun getFileswithPath(): MutableList<String> {

        val folderPath = "src${File.separator}files"
        val directoryPath = Paths.get(folderPath)
        logger.debug { "O caminho da pasta é $directoryPath" }
        var files: MutableList<String> = mutableListOf()

        if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
            Files.walkFileTree(directoryPath,
                EnumSet.noneOf(FileVisitOption::class.java),
                Int.MAX_VALUE,
                object : SimpleFileVisitor<Path>() {
                    @Throws(IOException::class)
                    override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
                        files.add(file.toString())
                        return FileVisitResult.CONTINUE
                    }

                    @Throws(IOException::class)
                    override fun visitFileFailed(file: Path?, exc: IOException?): FileVisitResult {
                        logger.error { "Could not visit the file" }
                        exc?.printStackTrace()
                        return FileVisitResult.CONTINUE
                    }
                })
        }

        return files


    }

    fun uploadFile(file: MultipartFile): String{

        val filePath = "src${File.separator}files${File.separator}${file.originalFilename}"

        try {

            val fout = FileOutputStream(filePath)
            fout.write(file.bytes)
            fout.close()

        } catch (e: Exception){
            e.printStackTrace()
            throw Exception("Erro ao enviar o arquivo: $e")
        }

        return filePath

    }

    fun downloadFile(filePath: String): String {

        val filenames = this.getFileswithPath()
        val contains: Boolean = filenames.contains(filePath)
        if (!contains) {
            throw Exception()
        }

        val file = ByteArrayInputStream(File(filePath).readBytes())
        val inputStreamResource = InputStreamResource(file)

        return fileConversorToString(inputStreamResource.inputStream)

    }

    fun updateFile(file: MultipartFile, originalFilePath: String){

        val filenames = this.getFileswithPath()
        val contains: Boolean = filenames.contains(originalFilePath)
        if (!contains){
            throw Exception()
        }

        try {

            val fout = FileOutputStream(originalFilePath)
            fout.write(file.bytes)
            fout.close()

        } catch (e: Exception) {
            e.printStackTrace()
            throw Exception("Erro ao enviar o arquivo: $e")
        }

    }

    fun fileConversorToInputStreamResource(file: MultipartFile): InputStream {

        val fileToISR = ByteArrayInputStream(file.inputStream.readBytes())
        return InputStreamResource(fileToISR).inputStream

    }

    fun fileConversorToString(inputStream: InputStream): String{
        Scanner(inputStream).useDelimiter("\\A").use { scanner ->
            return if (scanner.hasNext()) scanner.next() else ""
        }
    }
}