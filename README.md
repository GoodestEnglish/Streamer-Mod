# 觀眾場小幫手 - 模組版本
> 注意: 本模組需要使用MongoDB儲存觀眾資料, 還有使用Streamer-DiscordBot讓觀眾們在Discord輸入指令 
>> [MongoDB](https://www.mongodb.com/)  
>> [Streamer-DiscordBot](https://github.com/RealGoodestEnglish/Streamer-DiscordBot)  

## 如何使用
```
把jar檔案放到模組資料夾裏面
開啟遊戲,進入Hypixel伺服器
輸入指令 /sethost 設置MongoDB資料庫IP, 這個指令會返回一個callback訊息
	- 如果在執行指令中發生錯誤, 系統會顯示 "錯誤出現了! 基於安全原因, 請到log檔案查看錯誤"
	- 如果成功登入資料庫, 系統會顯示 "成功登入資料庫!"
	- 如果未能成功登入資料庫, 系統會顯示 "無法登入資料庫! 請檢查IP是否正確"
成功登入資料庫後, 你就可以使用指令 /streamer 開始跟觀眾同樂 😄
```

## 指令一覽
|指令|用途| 
|----------|:-------------:|
|/clearwaitlist|清除等候名單|
|/sethost <MongoDB資料庫IP> |設置MongoDB資料庫IP|
|/streamer <人數>|根據先後次序, 選出最適合的觀眾進入派對|

__*注意: sethost指令需要輸入資料庫IP, 資料庫IP請不要外洩__  
__**注意: 由於Hypixel 派對系統限制, streamer指令只能同時邀請五個玩家__


## 作者的話
這個模組應該不會繼續更新了, 如果想自己編寫特別功能進去這個模組, 歡迎  
這個是我第一個麥塊的模組, 而且只用了六個小時編寫, 寫得不好請見諒
