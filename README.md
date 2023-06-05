# File-Manager
* [Image-copmressor](#image-compressor)

##
Image compressor
Image compressor метод требует 4 параметра.
1. originalFilePath: String (получаем через uri.path)
2. localFilePath: String (получаем через context.filesDir.absolutePath)
3. filename: String (задаем название файлу)
4. maxSizeInMb: Int (передаем максимальный допустимый размер в мб)

## Пример
Возьмем фото с размером 4мб <br />
![image](https://github.com/nox1q/File-Manager/assets/45706924/52d54b68-dbb4-484b-a265-f970fc21f322)


![image](https://github.com/nox1q/File-Manager/assets/45706924/638693ee-8f7c-4f60-908e-bafc0b0f6511) <br />
Указываем максимальный размер в 1мб и получаем результат в ~0.7мб
